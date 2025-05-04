package com.aesopwow.subsubclipclop.domain.analysis.repository.cohortdouble;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisRemainHeatmapResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CohortDoubleAnalysisRemainHeatmapRepositoryImpl implements CohortDoubleAnalysisRemainHeatmapRepository {

    @Override
    public CohortDoubleAnalysisRemainHeatmapResponseDto requestDoubleAnalysisChart(String clusterType) {
        try {
            // ✅ 파일 로드 시도
            InputStream csvStream = getClass().getClassLoader().getResourceAsStream("mock/double-remain-heatmap.csv");

            // ✅ 디버깅 메시지
            if (csvStream == null) {
                System.out.println("❌ CSV 파일을 찾지 못했습니다.");
                throw new IllegalArgumentException("CSV 파일 없음");
            }

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

            return new CohortDoubleAnalysisRemainHeatmapResponseDto(title, content, columnLabels, dataRows);

        } catch (Exception e) {
            throw new RuntimeException("히트맵 차트 데이터 읽기 실패", e);
        }
    }

}
