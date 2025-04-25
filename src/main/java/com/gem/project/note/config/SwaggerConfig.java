package com.gem.project.note.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Note API",
        version = "v1",
        description = "노트 서비스 API 명세서입니다."
    )
)
public class SwaggerConfig {
}
