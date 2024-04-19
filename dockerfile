FROM maven:3.9.0-eclipse-temurin-11-alpine





# Copy automation framework code
COPY . /framework

# Set the working directory
WORKDIR /framework

# Expose port
EXPOSE 443
EXPOSE 80



# Install Maven dependencies
RUN mvn clean install -DskipTests

CMD ["mvn" , "test"]
