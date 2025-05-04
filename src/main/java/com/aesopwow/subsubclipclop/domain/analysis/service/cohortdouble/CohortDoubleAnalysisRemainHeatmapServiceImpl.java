package com.aesopwow.subsubclipclop.domain.analysis.service.cohortdouble;


import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisRemainHeatmapResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.repository.cohortdouble.CohortDoubleAnalysisRemainHeatmapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CohortDoubleAnalysisRemainHeatmapServiceImpl implements CohortDoubleAnalysisRemainHeatmapService {

    private final CohortDoubleAnalysisRemainHeatmapRepository heatmapRepository;

    @Override
    public CohortDoubleAnalysisRemainHeatmapResponseDto fetchDoubleAnalysisChart(String clusterType) {
        return heatmapRepository.requestDoubleAnalysisChart(clusterType);
    }
}
