package com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CohortDoubleAnalysisRemainHeatmapResponseDto {
    private String title;
    private String content;
    private List<String> columnLabels;
    private List<List<String>> dataRows;
}