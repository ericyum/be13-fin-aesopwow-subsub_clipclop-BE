package com.aesopwow.subsubclipclop.domain.analysis.service.cohortdouble;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisRemainHeatmapResponseDto;

public interface CohortDoubleAnalysisRemainHeatmapService {
    CohortDoubleAnalysisRemainHeatmapResponseDto fetchDoubleAnalysisChart(String clusterType);
}
