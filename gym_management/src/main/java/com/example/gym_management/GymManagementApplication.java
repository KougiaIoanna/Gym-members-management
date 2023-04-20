package com.example.gym_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.gym_management" })
public class GymManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(GymManagementApplication.class, args);
  }
}
