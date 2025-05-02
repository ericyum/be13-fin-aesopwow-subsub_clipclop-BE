package com.aesopwow.subsubclipclop.domain.analysis.service.cohortsingle;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisInsightRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisInsightResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.repository.cohortsingle.CohortSingleAnalysisInsightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CohortSingleAnalysisInsightServiceImpl implements CohortSingleAnalysisInsightService {

    private final CohortSingleAnalysisInsightRepository repository;

    @Override
    public CohortSingleAnalysisInsightResponseDto fetchInsight(CohortSingleAnalysisInsightRequestDto requestDto) {
        return repository.fetchInsight(requestDto.getClusterType());
    }
}