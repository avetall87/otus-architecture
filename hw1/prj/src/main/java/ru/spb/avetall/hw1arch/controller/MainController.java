package ru.spb.avetall.hw1arch.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping(path = "/health")
    public AppHealth health() {
        return new AppHealth(HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    private class AppHealth {
        private HttpStatus status;
    }

}
