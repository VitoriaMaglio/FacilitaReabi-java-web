package br.facilitareabi.com.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api") //essa é a parte raiz da api na url
public class ApplicationConfig extends Application {
}
