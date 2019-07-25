package cn.iocoder.mall.search.application.controller.users;

import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.SortingField;
import cn.iocoder.mall.search.api.ProductSearchService;
import cn.iocoder.mall.search.api.bo.ProductConditionBO;
import cn.iocoder.mall.search.api.bo.ProductPageBO;
import cn.iocoder.mall.search.api.dto.ProductConditionDTO;
import cn.iocoder.mall.search.api.dto.ProductSearchPageDTO;
import org.apache.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static cn.iocoder.common.framework.vo.CommonResult.success;

// TODO èŠ‹è‰¿ï¼Œæ?œç´¢å…³é”®å­—çš„é…?ç½®
// TODO èŠ‹è‰¿ï¼Œæ?œç´¢æ—¥å¿—

@RestController
@RequestMapping("users/product")
@Api("å•†å“?æ?œç´¢")
public class UsersProductSearchController {

    @Reference(validation = "true", version = "${dubbo.provider.ProductSearchService.version}")
    private ProductSearchService productSearchService;

    @GetMapping("/page") // TODO èŠ‹è‰¿ï¼Œå?Žé?¢æŠŠ BO æ”¹æˆ? VO
    public CommonResult<ProductPageBO> page(@RequestParam(value = "cid", required = false) Integer cid,
                                            @RequestParam(value = "keyword", required = false) String keyword,
                                            @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                            @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                            @RequestParam(value = "sortField", required = false) String sortField,
                                            @RequestParam(value = "sortOrder", required = false) String sortOrder) {
        // åˆ›å»º ProductSearchPageDTO å¯¹è±¡
        ProductSearchPageDTO productSearchPageDTO = new ProductSearchPageDTO().setCid(cid).setKeyword(keyword)
                .setPageNo(pageNo).setPageSize(pageSize);
        if (StringUtil.hasText(sortField) && StringUtil.hasText(sortOrder)) {
            productSearchPageDTO.setSorts(Collections.singletonList(new SortingField(sortField, sortOrder)));
        }
        // æ‰§è¡Œæ?œç´¢
        return success(productSearchService.getSearchPage(productSearchPageDTO));
    }

    @GetMapping("/condition") // TODO èŠ‹è‰¿ï¼Œå?Žé?¢æŠŠ BO æ”¹æˆ? VO
    public CommonResult<ProductConditionBO> condition(@RequestParam(value = "keyword", required = false) String keyword) {
        // åˆ›å»º ProductConditionDTO å¯¹è±¡
        ProductConditionDTO productConditionDTO = new ProductConditionDTO().setKeyword(keyword)
                .setFields(Collections.singleton(ProductConditionDTO.FIELD_CATEGORY));
        // æ‰§è¡Œæ?œç´¢
        return success(productSearchService.getSearchCondition(productConditionDTO));
    }

}
