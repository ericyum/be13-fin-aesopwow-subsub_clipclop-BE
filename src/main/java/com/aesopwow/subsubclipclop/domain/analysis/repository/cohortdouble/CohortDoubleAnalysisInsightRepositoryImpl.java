package com.aesopwow.subsubclipclop.domain.analysis.repository.cohortdouble;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisInsightResponseDto;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Repository
public class CohortDoubleAnalysisInsightRepositoryImpl implements CohortDoubleAnalysisInsightRepository {

    @Override
    public CohortDoubleAnalysisInsightResponseDto fetchInsight(String clusterType) {
        try {
            InputStream csvStream = getClass().getClassLoader().getResourceAsStream("mock/double-insight.csv");
            if (csvStream == null) throw new IllegalArgumentException("CSV 파일 없음");

            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream));
            reader.readLine(); // 헤더 skip

            String line = reader.readLine();
            String[] parts = line.split(",");

            String title = parts[0].trim();
            String content = parts[1].trim();

            return new CohortDoubleAnalysisInsightResponseDto(title, content);
        } catch (Exception e) {
            throw new RuntimeException("단일 인사이트 분석 실패", e);
        }
    }
}