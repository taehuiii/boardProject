# 📋 boardProject

boardProject는 사용자가 인증하고, 게시글을 작성하며, 댓글을 남길 수 있는 간단한 게시판 시스템을 구현한 Kotlin 기반 웹 애플리케이션입니다.
***

## 🛠 사용 기술

- **Java:** 17
- **Spring Boot:** 3.3.0
- **Kotlin:** 1.9.24
- **데이터베이스:** H2, PostgreSQL

## 🚀 주요 기능

- 사용자 인증 및 권한 부여
- 게시글 생성, 조회, 수정, 삭제
- 댓글 작성, 조회, 수정 삭제
- JWT를 이용한 보안


## 📋 사전 요구 사항

- JDK 17 이상
- Gradle

## 📦 의존성

이 프로젝트는 다음과 같은 의존성을 사용합니다:

- `spring-boot-starter-web`: 웹 애플리케이션을 구축하기 위한 스타터.
- `jackson-module-kotlin`: Kotlin 지원을 위한 Jackson 모듈.
- `kotlin-reflect`: Kotlin 리플렉션 라이브러리.
- `spring-boot-starter-data-jpa`: Spring Data JPA를 위한 스타터.
- `postgresql`: PostgreSQL JDBC 드라이버.
- `spring-boot-starter-validation`: Hibernate Validator와 함께 Java Bean Validation을 사용하기 위한 스타터.
- `springdoc-openapi-starter-webmvc-ui`: Spring WebMVC와 Springdoc OpenAPI 통합을 위한 스타터.
- `spring-boot-starter-security`: Spring Security를 위한 스타터.
- `jjwt`: JWT를 사용하기 위한 라이브러리.

## 📂 프로젝트 구조

이 프로젝트는 표준 Spring Boot 구조를 따릅니다:

```
boardProject
├── gradle
├── src
│   ├── main
│   │   ├── kotlin
│   │   │   └── boardProject
│   │   │       ├── config
│   │   │       ├── domain
│   │   │       │   ├── auth
│   │   │       │   │   ├── controller
│   │   │       │   │   ├── dto
│   │   │       │   │   ├── model
│   │   │       │   │   ├── repository
│   │   │       │   │   └── service
│   │   │       │   ├── post
│   │   │       │   │   ├── controller
│   │   │       │   │   ├── dto
│   │   │       │   │   ├── model
│   │   │       │   │   ├── repository
│   │   │       │   │   └── service
│   │   │       │   ├── exception
│   │   │       │   │   ├── dto
│   │   │       │   └── infra
│   │   │       │       ├── security
│   │   │       │       │   ├── config
│   │   │       │       │   └── jwt
│   │   │       │       └── SwaggerConfig
│   │   │       └── BoardProjectApplication.kt
│   └── resources
└── build.gradle.kts

```

## 🗂️ 데이터베이스 스키마
프로젝트는 다음과 같은 데이터베이스 스키마를 사용합니다:


### 🗃️ 테이블
- member: 사용자 정보를 저장합니다.
- post: 사용자가 작성한 게시글을 저장합니다.
- comment: 게시글에 대한 댓글을 저장합니다. 





***

#### [ Entity Relationship Diagram ]
<img width="500" alt="image" src="https://github.com/taehuiii/boardProject/assets/160212663/a5cafe1f-8b6f-44e9-b28c-748e9158aecb">





