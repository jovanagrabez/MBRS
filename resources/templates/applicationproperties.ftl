server.port = 8080
spring.application.name =${app_name}

spring.datasource.url=jdbc:mysql://localhost:3306/sep_nc?useSSL=false&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true

spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.hbm2ddl.import_files_sql_extractor=org.hibernate.tool.hbm2ddl.MultipleLinesSqlCommandExtractor
spring.datasource.username=root
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.show-sql=true

grpc.server.port=9090