package com.aesopwow.subsubclipclop.domain.analysis.service.cohortsingle;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisRemainHeatmapResponseDto;

public interface CohortSingleAnalysisRemainHeatmapService {
    CohortSingleAnalysisRemainHeatmapResponseDto fetchSingleAnalysisChart(String clusterType);
}
