#!/bin/bash
set -e

# Define Docker images and container names
EUREKA_SERVER_IMAGE="pccw-assignment-eureka-server:latest"
EUREKA_SERVER_CONTAINER="pccw-assignment-eureka-server"
ZUUL_IMAGE="pccw-assignment-zuul:latest"
ZUUL_CONTAINER="pccw-assignment-zuul"
USER_SERVICE_IMAGE="pccw-assignment-user-service:latest"
USER_SERVICE_CONTAINER="pccw-assignment-user-service"
EMAIL_SERVICE_IMAGE="pccw-assignment-email-service:latest"
EMAIL_SERVICE_CONTAINER="pccw-assignment-email-service"
NETWORK_NAME="my-network"

# Function to build the service
build_service() {
    local service_dir=$1

    echo "Building Docker image for $service_dir..."
    cd $service_dir
    mvn clean package docker:build
    cd - # Return to the original directory
}

# Function to start the service
start_service() {
    local service=$1
    local container=$2
    local image=$3
    local extra_args=$4

    if [ "$(docker ps -aq -f name=$container)" ]; then
        echo "Stopping and removing existing container $container..."
        docker stop $container
        docker rm $container
    fi

    echo "Starting $service..."
    docker run --name $container --network $NETWORK_NAME $extra_args -d $image
}

# Create Docker network if it does not exist
create_network() {
    if ! docker network ls | grep -q $NETWORK_NAME; then
        echo "Network $NETWORK_NAME does not exist. Creating it..."
        docker network create $NETWORK_NAME
    else
        echo "Network $NETWORK_NAME already exists."
    fi
}

# Build and start all services
build_and_start_all() {
    build_service "eureka-server"
    build_service "zuul"
    build_service "user-service"
    build_service "email-service"

    start_service "Eureka Server" $EUREKA_SERVER_CONTAINER $EUREKA_SERVER_IMAGE "-p 9094:9094"
    start_service "Zuul" $ZUUL_CONTAINER $ZUUL_IMAGE "-p 9095:9095"
    start_service "User Service" $USER_SERVICE_CONTAINER $USER_SERVICE_IMAGE "-p 9999:9999"
    start_service "Email Service" $EMAIL_SERVICE_CONTAINER $EMAIL_SERVICE_IMAGE "-p 9998:9998"
}

# Check if any arguments are passed
if [ $# -eq 0 ]; then
    echo "No services specified. Please provide one or more service names as arguments."
    echo "Available options are:"
    echo "  eureka-server   - Build and start the Eureka Server service"
    echo "  zuul            - Build and start the Zuul service"
    echo "  user-service    - Build and start the User Service"
    echo "  email-service   - Build and start the Email Service"
    echo "  all             - Build and start all services"
    exit 1
fi

# Create Docker network
create_network

# Parse command-line arguments to build and start services
if [ "$1" == "all" ]; then
    build_and_start_all
else
    for service in "$@"
    do
        case $service in
            eureka-server)
                build_service "eureka-server"
                start_service "Eureka Server" $EUREKA_SERVER_CONTAINER $EUREKA_SERVER_IMAGE "-p 9094:9094"
                ;;
            zuul)
                build_service "zuul"
                start_service "Zuul" $ZUUL_CONTAINER $ZUUL_IMAGE "-p 9095:9095"
                ;;
            user-service)
                build_service "user-service"
                start_service "User Service" $USER_SERVICE_CONTAINER $USER_SERVICE_IMAGE "-p 9999:9999"
                ;;
            email-service)
                build_service "email-service"
                start_service "Email Service" $EMAIL_SERVICE_CONTAINER $EMAIL_SERVICE_IMAGE "-p 9998:9998"
                ;;
            *)
                echo "Unknown service: $service"
                echo "Available options are:"
                echo "  eureka-server   - Build and start the Eureka Server service"
                echo "  zuul            - Build and start the Zuul service"
                echo "  user-service    - Build and start the User Service"
                echo "  email-service   - Build and start the Email Service"
                echo "  all             - Build and start all services"
                ;;
        esac
    done
fi

echo "Selected services built and started successfully."
