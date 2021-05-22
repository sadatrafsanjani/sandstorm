package com.rafsanjani.sandstorm.service;

import com.rafsanjani.sandstorm.model.Area;

public interface AreaService {

    Area saveArea(Area area);
    Iterable<Area> getAreas();
    Area getArea(long id);
}
