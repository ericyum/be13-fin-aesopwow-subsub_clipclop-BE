package com.aesopwow.subsubclipclop.domain.analysis.repository.cohortdouble;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisVisualizationResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisVisualizationResponseDto;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;

@Repository
public class CohortDoubleAnalysisVisualizationRepositoryImpl implements CohortDoubleAnalysisVisualizationRepository {

    @Override
    public CohortDoubleAnalysisVisualizationResponseDto fetchVisualization(String clusterType) {
        try {
            InputStream csvStream = getClass().getClassLoader().getResourceAsStream("mock/double-visualization.csv");
            if (csvStream == null) throw new IllegalArgumentException("CSV 파일 없음");

            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream));
            reader.readLine(); // header skip

            String line = reader.readLine();
            String[] parts = line.split(",");

            String title = parts[0].trim();
            String imageAPath = parts[1].trim();
            String imageBPath = parts[2].trim();

            InputStream imageAStream = getClass().getClassLoader().getResourceAsStream(imageAPath);
            InputStream imageBStream = getClass().getClassLoader().getResourceAsStream(imageBPath);

            if (imageAStream == null || imageBStream == null) {
                throw new IllegalArgumentException("이미지 파일을 찾을 수 없습니다.");
            }

            String base64A = "data:image/png;base64," + Base64.getEncoder().encodeToString(imageAStream.readAllBytes());
            String base64B = "data:image/png;base64," + Base64.getEncoder().encodeToString(imageBStream.readAllBytes());

            return new CohortDoubleAnalysisVisualizationResponseDto(title, base64A, base64B);

        } catch (Exception e) {
            throw new RuntimeException("시각화 이미지 분석 실패", e);
        }
    }
}