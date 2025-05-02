package com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CohortSingleAnalysisVisualizationResponseDto {
    private String title;
    private String imageBase64A;  // 첫 번째 이미지
    private String imageBase64B;  // 두 번째 이미지
}