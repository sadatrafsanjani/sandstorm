package com.rafsanjani.sandstorm.service.implementation;

import com.rafsanjani.sandstorm.model.Area;
import com.rafsanjani.sandstorm.repository.AreaRepository;
import com.rafsanjani.sandstorm.service.abstraction.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AreaServiceImpl implements AreaService {

    private AreaRepository areaRepository;

    @Autowired
    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public Area saveArea(Area area){

        return areaRepository.save(area);
    }

    @Override
    public Iterable<Area> getAreas(){

        return areaRepository.findAll();
    }

    @Override
    public Area getArea(long id){

        Optional<Area> areaOptional = areaRepository.findById(id);

        if (areaOptional.isPresent()) {

            return areaOptional.get();
        }

        return null;
    }
}
