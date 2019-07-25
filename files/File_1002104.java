package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.core.common.constant.state.CommonStatus;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.system.entity.DictType;
import cn.stylefeng.guns.modular.system.mapper.DictTypeMapper;
import cn.stylefeng.guns.modular.system.model.params.DictTypeParam;
import cn.stylefeng.guns.modular.system.model.result.DictTypeResult;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * å­—å…¸ç±»åž‹è¡¨ æœ?åŠ¡å®žçŽ°ç±»
 * </p>
 *
 * @author stylefeng
 * @since 2019-03-13
 */
@Service
public class DictTypeService extends ServiceImpl<DictTypeMapper, DictType> {

    /**
     * æ–°å¢ž
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    public void add(DictTypeParam param) {

        //åˆ¤æ–­æ˜¯å?¦å·²ç»?å­˜åœ¨å?Œç¼–ç ?æˆ–å?Œå??ç§°å­—å…¸
        QueryWrapper<DictType> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("code", param.getCode()).or().eq("name", param.getName());
        List<DictType> list = this.list(dictQueryWrapper);
        if (list != null && list.size() > 0) {
            throw new ServiceException(BizExceptionEnum.DICT_EXISTED);
        }

        DictType entity = getEntity(param);

        //è®¾ç½®çŠ¶æ€?
        entity.setStatus(CommonStatus.ENABLE.getCode());

        this.save(entity);
    }

    /**
     * åˆ é™¤
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    public void delete(DictTypeParam param) {
        this.removeById(getKey(param));
    }

    /**
     * æ›´æ–°
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    public void update(DictTypeParam param) {
        DictType oldEntity = getOldEntity(param);
        DictType newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);

        //åˆ¤æ–­ç¼–ç ?æ˜¯å?¦é‡?å¤?
        QueryWrapper<DictType> wrapper = new QueryWrapper<DictType>()
                .and(i -> i.eq("code", newEntity.getCode()).or().eq("name", newEntity.getName()))
                .and(i -> i.ne("dict_type_id", newEntity.getDictTypeId()));
        int dicts = this.count(wrapper);
        if (dicts > 0) {
            throw new ServiceException(BizExceptionEnum.DICT_EXISTED);
        }

        this.updateById(newEntity);
    }

    /**
     * æŸ¥è¯¢å?•æ?¡æ•°æ?®ï¼ŒSpecificationæ¨¡å¼?
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    public DictTypeResult findBySpec(DictTypeParam param) {
        return null;
    }

    /**
     * æŸ¥è¯¢åˆ—è¡¨ï¼ŒSpecificationæ¨¡å¼?
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    public List<DictTypeResult> findListBySpec(DictTypeParam param) {
        return null;
    }

    /**
     * æŸ¥è¯¢åˆ†é¡µæ•°æ?®ï¼ŒSpecificationæ¨¡å¼?
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    public LayuiPageInfo findPageBySpec(DictTypeParam param) {
        Page pageContext = getPageContext();
        QueryWrapper<DictType> objectQueryWrapper = new QueryWrapper<>();
        if (ToolUtil.isNotEmpty(param.getCondition())) {
            objectQueryWrapper.and(i -> i.eq("code", param.getCondition()).or().eq("name", param.getCondition()));
        }
        if (ToolUtil.isNotEmpty(param.getStatus())) {
            objectQueryWrapper.and(i -> i.eq("status", param.getStatus()));
        }
        if (ToolUtil.isNotEmpty(param.getSystemFlag())) {
            objectQueryWrapper.and(i -> i.eq("system_flag", param.getSystemFlag()));
        }

        pageContext.setAsc("sort");

        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(DictTypeParam param) {
        return param.getDictTypeId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private DictType getOldEntity(DictTypeParam param) {
        return this.getById(getKey(param));
    }

    private DictType getEntity(DictTypeParam param) {
        DictType entity = new DictType();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
