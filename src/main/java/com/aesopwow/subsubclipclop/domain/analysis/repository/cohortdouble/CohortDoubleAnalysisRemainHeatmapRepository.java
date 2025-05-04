package com.aesopwow.subsubclipclop.domain.analysis.repository.cohortdouble;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisRemainHeatmapResponseDto;

public interface CohortDoubleAnalysisRemainHeatmapRepository {
    CohortDoubleAnalysisRemainHeatmapResponseDto requestDoubleAnalysisChart(String clusterType);
}
