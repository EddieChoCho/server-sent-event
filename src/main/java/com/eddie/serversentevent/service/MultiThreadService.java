package com.eddie.serversentevent.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MultiThreadService {

    private final ExecutorService executorService;

    public MultiThreadService(@Value("${thread.numbers}") final int DEFAULT_THEAD_NUMBERS){
        executorService = Executors.newFixedThreadPool(DEFAULT_THEAD_NUMBERS);
    }

    public ExecutorService getExecutorService(){
        return this.executorService;
    }


}
