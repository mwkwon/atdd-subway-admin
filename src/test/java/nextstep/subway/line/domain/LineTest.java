package nextstep.subway.line.domain;

import nextstep.subway.line.domain.wrappers.Sections;
import nextstep.subway.section.domain.Section;
import nextstep.subway.station.domain.Station;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Line entity 테스트")
class LineTest {

    @Test
    @DisplayName("아이디 기준 노선 조회 결과가 없을 시 에러 정상 발생")
    void checkNullLine() {
        Optional<Line> emptyLine = Optional.empty();
        assertThatThrownBy(() -> Line.getNotNullLine(emptyLine)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("노선 데이터 변경")
    void update() {
        Line expected = new Line("분당선", "bg-red-600");
        Line updateLine = new Line("구분당선", "bg-red-600");
        expected.update(updateLine);
        assertThat(expected).isEqualTo(updateLine);
    }

    @Test
    @DisplayName("노선 정보에 구간 정보 add")
    void addSection() {
        Line line = new Line("신분당선", "bg - red - 600");
        Station upStation = new Station("강남역");
        Station downStation = new Station("광교역");
        Section section = new Section(upStation, downStation, 100);
        line.addSection(section);
        assertThat(line).isEqualTo(new Line("신분당선", "bg - red - 600", new Sections(Arrays.asList(section))));
    }
}
