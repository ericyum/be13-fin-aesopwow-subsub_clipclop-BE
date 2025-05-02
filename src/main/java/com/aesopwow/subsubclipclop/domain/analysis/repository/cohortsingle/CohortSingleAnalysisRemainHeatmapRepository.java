package com.aesopwow.subsubclipclop.domain.analysis.repository.cohortsingle;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisRemainHeatmapResponseDto;

public interface CohortSingleAnalysisRemainHeatmapRepository {
    CohortSingleAnalysisRemainHeatmapResponseDto requestSingleAnalysisChart(String clusterType);
}
