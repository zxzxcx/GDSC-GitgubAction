# 1단계: 빌드
FROM gradle:7.5-jdk17 AS builder
WORKDIR /app

# 프로젝트 파일 복사 (빌드에 필요한 모든 파일)
COPY . .

# Gradle 빌드 실행 (bootJar 생성)
RUN ./gradlew bootJar --no-daemon

# 2단계: 실행용 경량 이미지
FROM openjdk:17-jdk-alpine
WORKDIR /app

# 빌드 결과물 복사
COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
