package com.aesopwow.subsubclipclop.domain.analysis.repository.cohortsingle;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisInsightResponseDto;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Repository
public class CohortSingleAnalysisInsightRepositoryImpl implements CohortSingleAnalysisInsightRepository {

    @Override
    public CohortSingleAnalysisInsightResponseDto fetchInsight(String clusterType) {
        try {
            InputStream csvStream = getClass().getClassLoader().getResourceAsStream("mock/single-insight.csv");
            if (csvStream == null) throw new IllegalArgumentException("CSV 파일 없음");

            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream));
            reader.readLine(); // 헤더 skip

            String line = reader.readLine();
            String[] parts = line.split(",");

            String title = parts[0].trim();
            String content = parts[1].trim();

            return new CohortSingleAnalysisInsightResponseDto(title, content);
        } catch (Exception e) {
            throw new RuntimeException("단일 인사이트 분석 실패", e);
        }
    }
}