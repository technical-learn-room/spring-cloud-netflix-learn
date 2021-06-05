package com.me.servicea;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TestResponse {
    private String name;
    private int age;
    private String gradeClassNumber;

    public void update(String name, int age, String gradeClassNumber) {
        this.name = name;
        this.age = age;
        this.gradeClassNumber = gradeClassNumber;
    }
}
