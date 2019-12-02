package com.mia.eatgo.application;

import com.mia.eatgo.domain.Region;
import com.mia.eatgo.domain.RegionRepository;
import com.mia.eatgo.domain.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getRegions() {

        List<Region> regions = regionRepository.findAll();
        return regions;
    }

    public Region addRegion(String name) {
        Region region = Region.builder().name(name).build();
        return regionRepository.save(region);
    }
}
