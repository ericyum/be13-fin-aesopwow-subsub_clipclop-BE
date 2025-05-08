package com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CohortSingleAnalysisRemainHeatmapResponseDto {
    private String content;
    private List<String> columnLabels;
    private List<List<String>> dataRows;
}