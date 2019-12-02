package com.mia.eatgo.application;

import com.mia.eatgo.domain.Region;
import com.mia.eatgo.domain.RegionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RegionServiceTest {

    @Mock
    RegionService regionService;

    @Mock
    RegionRepository regionRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        regionService = new RegionService(regionRepository);
    }
    @Test
    public void getRegions() {
        List<Region> mockRegions = new ArrayList<>();
        mockRegions.add(Region.builder().name("Seoul").build());

        given(regionRepository.findAll()).willReturn(mockRegions);
        List<Region> regions = regionService.getRegions();
        Region region = regions.get(0);
        assertThat(region.getName(), is("Seoul"));
    }

    @Test
    public void addRegion() {
        given(regionRepository.save(any())).will(invocation -> {
            Region region = invocation.getArgument(0);
            region.setId(1L);
            return region;
        });
        Region created = regionService.addRegion("Seoul");
        verify(regionRepository).save(any());
        assertThat(created.getName(), is("Seoul"));
    }

}