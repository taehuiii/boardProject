# 📋 boardProject

boardProject는 사용자가 인증하고, 게시글을 작성하며, 댓글을 남길 수 있는 간단한 게시판 시스템을 구현한 Kotlin 기반 웹 애플리케이션입니다.
***

## 🛠 사용 기술

- **Java:** 17
- **Spring Boot:** 3.3.1
- **Kotlin:** 1.9.24
- **데이터베이스:** H2, PostgreSQL

## 🚀 주요 기능

- 팀 관리: 팀을 생성하고 팀원을 추가하고, 권한을 변경하는 등의 관리를 할 수 있습니다.
- 팀원 모집 관리: 팀원 모집글을 작성하고, 신청 내역을 확인하며 응답할 수 있습니다.
- 경기 일정 관리: 날짜와 지역별로 경기를 일정에 맞춰 모집, 또는 신청할 수 있습니다.
- 경기 및 팀 검색 : 원하는 날짜, 지역 별로 검색을 할 수 있습니다.
- 팀 랭킹 : 경기 후 평가 점수를 계산하여 팀 별 랭킹을 확인할 수 있습니다.
- 사용자 프로필 관리: 개인 정보를 편집하고, 프로필을 관리할 수 있습니다.

## 🖥️ 서비스 아키텍처
<img width="637" alt="image" src="https://github.com/user-attachments/assets/84daf55d-fc0c-4635-b3ef-655b95860fe1">

#### [ Entity Relationship Diagram ]
<img width="500" alt="image" src="https://github.com/taehuiii/boardProject/assets/160212663/a5cafe1f-8b6f-44e9-b28c-748e9158aecb">

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






