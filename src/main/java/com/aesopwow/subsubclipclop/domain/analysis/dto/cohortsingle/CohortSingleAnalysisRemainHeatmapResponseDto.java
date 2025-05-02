package com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CohortSingleAnalysisRemainHeatmapResponseDto {
    private String title;
    private String content;
    private List<String> columnLabels;
    private List<List<String>> dataRows;
}