package com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CohortDoubleAnalysisRemainHeatmapResponseDto {
    private String firstContent;
    private List<String> firstColumnLabels;
    private List<List<String>> firstDataRows;

    private String secondContent;
    private List<String> secondColumnLabels;
    private List<List<String>> secondDataRows;
}