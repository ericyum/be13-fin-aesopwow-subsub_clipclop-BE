package com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CohortDoubleAnalysisUserDataRequestDto {
    private List<String> fields; // [나이, 국가 등등]
}