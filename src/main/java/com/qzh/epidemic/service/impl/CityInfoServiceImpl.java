package com.qzh.epidemic.service.impl;

import com.qzh.epidemic.entity.CityInfo;
import com.qzh.epidemic.mapper.CityInfoMapper;
import com.qzh.epidemic.service.CityInfoService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CityInfoServiceImpl
 * @Author DiangD
 * @Date 2020/2/26
 * @Version 1.0
 * @Description cityInfo服务实现类
 **/
@Service
@CacheConfig(cacheNames = {"cache-cityInfo"})
public class CityInfoServiceImpl implements CityInfoService {
    @Resource
    private CityInfoMapper cityInfoMapper;

    /**
     * @param cityInfo cityInfo
     * @return 成功插入的个数
     */
    @Override
    @CacheEvict(allEntries = true,beforeInvocation = true)
    public int addCityInfo(CityInfo cityInfo) {
        return cityInfoMapper.addCityInfo(cityInfo);
    }



    /**
     * @param cityInfos cityInfoList
     * @return 成功插入的个数
     */
    @Override
    public int addCityInfoList(List<CityInfo> cityInfos) {
        return cityInfoMapper.addCityInfoList(cityInfos);
    }

    /**
     * @param provinceId 省份id
     * @return List<CityInfo>
     */
    @Override
    @Cacheable(key = "#a0")
    public List<CityInfo> getCityInfoByProvinceId(int provinceId) {
        return cityInfoMapper.selectByProvinceId(provinceId);
    }
}
