package com.myimooc.boot.small.server.service.impl;

import com.myimooc.boot.small.server.mapper.AreaMapper;
import com.myimooc.boot.small.server.po.Area;
import com.myimooc.boot.small.server.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <br>
 * 标题: 区域业务�?务实现<br>
 * �??述: 区域业务�?务<br>
 *
 * @author zc
 * @date 2018/04/17
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> listAll() {
        return areaMapper.listAll();
    }

    @Override
    public Area findById(Integer areaId) {
        return areaMapper.findById(areaId);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Boolean create(Area area) {
        if (area != null) {
            return areaMapper.create(area) == 1;
        }
        throw new RuntimeException("区域信�?��?能为空�?");
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Boolean update(Area area) {
        if (area != null) {
            area.setLastEditTime(new Date());
            return areaMapper.update(area) == 1;
        }
        throw new RuntimeException("区域信�?��?能为空�?");
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Boolean delete(Integer areaId) {
        if (areaId > 0) {
            return areaMapper.delete(areaId) == 1;
        }
        throw new RuntimeException("区域ID�?能为空�?");
    }
}
