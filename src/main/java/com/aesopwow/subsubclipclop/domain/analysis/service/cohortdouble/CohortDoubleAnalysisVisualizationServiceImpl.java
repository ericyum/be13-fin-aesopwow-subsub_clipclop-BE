package com.aesopwow.subsubclipclop.domain.analysis.service.cohortdouble;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisVisualizationRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisVisualizationResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisVisualizationRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisVisualizationResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.repository.cohortdouble.CohortDoubleAnalysisVisualizationRepository;
import com.aesopwow.subsubclipclop.domain.analysis.repository.cohortsingle.CohortSingleAnalysisVisualizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CohortDoubleAnalysisVisualizationServiceImpl implements CohortDoubleAnalysisVisualizationService {

    private final CohortDoubleAnalysisVisualizationRepository repository;

    @Override
    public CohortDoubleAnalysisVisualizationResponseDto fetchVisualization(CohortDoubleAnalysisVisualizationRequestDto requestDto) {
        return repository.fetchVisualization(requestDto.getClusterType());
    }
}