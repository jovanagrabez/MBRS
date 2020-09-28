server:
  port: 8080
spring:
  mvc:
    view:
      prefix: /WEB-INF/jsp/
      suffix: .jsp
  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    username: sa
    password: sa
    sql-script-encoding: UTF-8
    initialization-mode: always
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: create
      nameing:
        strategy: org.hibernate.cfg.ImprovedNamingStrategy
    properties:
      hibernate:
        dialect: org.hibernate.dialect.H2Dialect
  main:
    allow-bean-definition-overriding: true
grpc:
  port: 6565
