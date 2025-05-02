package com.aesopwow.subsubclipclop.domain.analysis.service.cohortsingle;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisRemainHeatmapResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.repository.cohortsingle.CohortSingleAnalysisRemainHeatmapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CohortSingleAnalysisRemainHeatmapServiceImpl implements CohortSingleAnalysisRemainHeatmapService {

    private final CohortSingleAnalysisRemainHeatmapRepository heatmapRepository;

    @Override
    public CohortSingleAnalysisRemainHeatmapResponseDto fetchSingleAnalysisChart(String clusterType) {
        return heatmapRepository.requestSingleAnalysisChart(clusterType);
    }
}
