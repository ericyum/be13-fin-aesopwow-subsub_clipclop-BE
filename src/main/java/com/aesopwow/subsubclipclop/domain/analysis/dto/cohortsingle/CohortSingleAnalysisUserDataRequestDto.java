package com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CohortSingleAnalysisUserDataRequestDto {
    private List<String> fields; // [나이, 국가 등등]
}