package com.api.kakaochatbot.common.util;

import java.util.Arrays;

public enum CourseType {
    FamilyCourse("C0112", "가족코스"),
    AloneCourse("C0113", "나홀로코스"),
    HealingCourse("C0114", "힐링코스"),
    OnFootCourse("C0115", "도보코스"),
    CampingCourse("C0116", "캠핑코스"),
    TasteCourse("C0117", "맛코스");

    private String code;
    private String codeName;

    CourseType(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String GetCode() {
        return code;
    }

    public String GetCodeName() {
        return codeName;
    }

    //코드성 데이터 배열 생성
    public static CourseType[] CreateCourseCode() {
        return CourseType.class.getEnumConstants();
    }

    //특정 코드가 존재여부 확인
    public static boolean ExistCourseCodeName(CourseType[] courseCodes, String codeName) {
        CourseType courseEnum = FindCourseCodeName(courseCodes, codeName);

        return courseEnum.GetCodeName() == codeName;
    }

    //특정 코드 데이터 찾기
    public static CourseType FindCourseCodeName(CourseType[] courseCodes, String codeName) {
        CourseType courseEnum = Arrays.stream(courseCodes).filter(f -> f.codeName.equals(codeName)).findFirst().orElse(null);

        return courseEnum;
    }
}
