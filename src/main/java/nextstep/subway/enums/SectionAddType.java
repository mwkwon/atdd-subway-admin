package nextstep.subway.enums;

import nextstep.subway.line.domain.LineStation;
import nextstep.subway.line.domain.wrappers.LineStations;

public enum SectionAddType {
    NEW_UP, NEW_BETWEEN, NEW_DOWN;

    public static SectionAddType calcAddType(LineStations lineStations, LineStation lineStation) {
        if (lineStations.isNewUpLineStation(lineStation)) {
            return NEW_UP;
        }
        if (lineStations.isNewDownLineStation(lineStation)) {
            return NEW_DOWN;
        }
        return NEW_BETWEEN;
    }
}
