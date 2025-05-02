package com.aesopwow.subsubclipclop.domain.analysis.service.cohortsingle;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisUserDataRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisUserDataResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.repository.cohortsingle.CohortSingleAnalysisUserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CohortSingleAnalysisUserDataServiceImpl implements CohortSingleAnalysisUserDataService {

    private final CohortSingleAnalysisUserDataRepository userDataRepository;

    @Override
    public CohortSingleAnalysisUserDataResponseDto fetchUserData(CohortSingleAnalysisUserDataRequestDto requestDto) {
        return userDataRepository.fetchUserData(requestDto);
    }
}