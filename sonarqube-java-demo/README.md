# SonarQube Java + Maven Demo

This project is designed for Jenkins + Maven + SonarQube training.

It contains:
- A deliberately problematic version of Java code under `src/main/java`
- Unit tests under `src/test/java`
- Maven configuration for compilation, testing, JaCoCo coverage, and SonarQube analysis
- A fixed version of the main Java class in `fixed-version/OrderService.java`

## Prerequisites

- Java 17+
- Maven 3.8+
- A running SonarQube server
- A SonarQube token

## Run locally

```bash
mvn clean test
mvn verify
```

## Run SonarQube analysis

Replace the URL and token:

```bash
mvn clean verify sonar:sonar   -Dsonar.projectKey=sonarqube-java-demo   -Dsonar.projectName=sonarqube-java-demo   -Dsonar.host.url=http://localhost:9000   -Dsonar.token=YOUR_SONAR_TOKEN
```

If SonarQube is running in Docker and Maven runs on the host, use:
`http://localhost:9000`.

If Maven runs inside a Docker container on the same Docker network as SonarQube, use:
`http://sonarqube:9000`.

## Suggested training flow

1. Run `mvn clean verify`.
2. Run SonarQube analysis.
3. Review issues in SonarQube.
4. Replace `src/main/java/com/example/demo/OrderService.java` with the fixed version.
5. Run tests and SonarQube analysis again.
6. Compare the issue count, test result, and coverage.

## Important note

Some SonarQube findings depend on the installed SonarJava analyzer version and quality profile. The intentionally problematic code demonstrates common categories such as:
- Null-safety problems
- Resource handling
- Weak exception handling
- Duplicated logic
- Hard-coded values
- Unnecessary complexity
- Poor naming and maintainability concerns

The exact issue list can vary by SonarQube version and quality profile.
