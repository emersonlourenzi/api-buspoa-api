package br.com.buspoa.contract.v1.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.buspoa.contract"))
                .paths(regex("/v1.*"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Prova de avaliação TechnoCorp")
                .description("Prova de avaliação técnica BackEnd.\n" +
                        "Java 11 \n" +
                        "Spring \n" +
                        "Lombok - Utilizei para o código ficar menos verboso, " +
                        "assim enxugando GETTERS, SETTERS, etc...\n" +
                        "Banco de dados MongoDB - Utilizei pois  na minha visão se encaixava melhor na api, " +
                        "e ao meu ver lida melhor com o json.\n" +
                        "Docker - Utilizei um conteiner do mongo, conclui que seria mais vantajoso e agil, " +
                        "do que instalar o banco na maquina.")
                .version("1.0")
                .contact(new Contact("Emerson Lourenzi",
                        "https://github.com/emersonlourenzi/buspoa",
                        "emersonl.lourenzi@gmail.com"))
                .build();
    }
}
