FROM gradle:8.12-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle installDist --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/install/campus-hub/ /app/
ENV PORT=8080
EXPOSE 8080
CMD ["/app/bin/campus-hub"]
