package com.aesopwow.subsubclipclop.domain.analysis.repository.cohortsingle;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.*;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Base64;

@Repository
public class CohortSingleAnalysisRepositoryImpl implements CohortSingleAnalysisRepository {

    @Override
    public CohortSingleAnalysisInsightResponseDto fetchInsight(String clusterType) {
        try {
            InputStream csvStream = getClass().getClassLoader().getResourceAsStream("mock/single-insight.csv");
            if (csvStream == null) throw new IllegalArgumentException("CSV 파일 없음");

            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream));
            reader.readLine(); // 헤더 skip
            String[] parts = reader.readLine().split(",");

            return new CohortSingleAnalysisInsightResponseDto(parts[0].trim(), parts[1].trim());
        } catch (Exception e) {
            throw new RuntimeException("단일 인사이트 분석 실패", e);
        }
    }

    @Override
    public CohortSingleAnalysisRemainHeatmapResponseDto fetchRemainHeatmap(String clusterType) {
        try {
            InputStream csvStream = getClass().getClassLoader().getResourceAsStream("mock/single-remain-heatmap.csv");
            if (csvStream == null) throw new IllegalArgumentException("CSV 파일 없음");

            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream));
            String line;
            String title = "";
            String content = "";
            List<String> columnLabels = new ArrayList<>();
            List<List<String>> dataRows = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                switch (line.trim()) {
                    case "title":
                        title = reader.readLine().trim();
                        break;
                    case "content":
                        content = reader.readLine().trim();
                        break;
                    case "columns":
                        columnLabels = Arrays.asList(reader.readLine().split(","));
                        break;
                    case "rows":
                        while ((line = reader.readLine()) != null) {
                            dataRows.add(Arrays.asList(line.split(",")));
                        }
                        break;
                }
            }
            return new CohortSingleAnalysisRemainHeatmapResponseDto(title, content, columnLabels, dataRows);

        } catch (Exception e) {
            throw new RuntimeException("히트맵 차트 데이터 읽기 실패", e);
        }
    }

    @Override
    public CohortSingleAnalysisVisualizationResponseDto fetchVisualization(String clusterType) {
        try {
            InputStream csvStream = getClass().getClassLoader().getResourceAsStream("mock/single-visualization.csv");
            if (csvStream == null) throw new IllegalArgumentException("CSV 파일 없음");

            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream));
            reader.readLine(); // 헤더 skip
            String[] parts = reader.readLine().split(",");

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

            return new CohortSingleAnalysisVisualizationResponseDto(title, base64A, base64B);
        } catch (Exception e) {
            throw new RuntimeException("시각화 이미지 분석 실패", e);
        }
    }

    @Override
    public CohortSingleAnalysisUserDataResponseDto fetchUserData(CohortSingleAnalysisUserDataRequestDto requestDto) {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("mock/single-user-data.csv");
            if (stream == null) throw new IllegalArgumentException("single-user-data.csv 파일 없음");

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String[] headers = reader.readLine().split(",");
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
            return new CohortSingleAnalysisUserDataResponseDto(table);
        } catch (Exception e) {
            throw new RuntimeException("유저 데이터 로딩 실패", e);
        }
    }
}