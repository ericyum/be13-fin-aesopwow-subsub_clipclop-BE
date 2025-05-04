package com.aesopwow.subsubclipclop.domain.analysis.service.cohortdouble;


import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisUserDataRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisUserDataResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.repository.cohortdouble.CohortDoubleAnalysisUserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CohortDoubleAnalysisUserDataServiceImpl implements CohortDoubleAnalysisUserDataService {

    private final CohortDoubleAnalysisUserDataRepository userDataRepository;

    @Override
    public CohortDoubleAnalysisUserDataResponseDto fetchUserData(CohortDoubleAnalysisUserDataRequestDto requestDto){
        return userDataRepository.fetchUserData(requestDto);
    }
}