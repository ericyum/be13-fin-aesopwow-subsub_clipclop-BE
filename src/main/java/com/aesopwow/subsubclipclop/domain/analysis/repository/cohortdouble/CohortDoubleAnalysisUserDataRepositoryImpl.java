package com.aesopwow.subsubclipclop.domain.analysis.repository.cohortdouble;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisUserDataRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisUserDataResponseDto;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CohortDoubleAnalysisUserDataRepositoryImpl implements CohortDoubleAnalysisUserDataRepository {

    @Override
    public CohortDoubleAnalysisUserDataResponseDto fetchUserData(CohortDoubleAnalysisUserDataRequestDto requestDto) {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("mock/double-user-data.csv");
            if (stream == null) throw new IllegalArgumentException("double-user-data.csv 파일 없음");

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String[] headers = reader.readLine().split(","); // 전체 컬럼

            List<Map<String, String>> table = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> row = new LinkedHashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    String fieldName = headers[i].trim();
                    if (requestDto.getFields().contains(fieldName)) {
                        row.put(fieldName, values[i].trim());
                    }
                }
                table.add(row);
            }
            return new CohortDoubleAnalysisUserDataResponseDto(table);

        } catch (Exception e) {
            throw new RuntimeException("유저 데이터 로딩 실패", e);
        }
    }
}