package cn.iocoder.mall.promotion.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import cn.iocoder.mall.product.api.ProductSpuService;
import cn.iocoder.mall.product.api.bo.ProductSpuBO;
import cn.iocoder.mall.promotion.api.ProductRecommendService;
import cn.iocoder.mall.promotion.api.bo.ProductRecommendBO;
import cn.iocoder.mall.promotion.api.bo.ProductRecommendPageBO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendAddDTO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendPageDTO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendUpdateDTO;
import cn.iocoder.mall.promotion.application.convert.ProductRecommendConvert;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsProductRecommendPageVO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsProductRecommendVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("admins/product_recommend")
@Api("å•†å“?æŽ¨è??æ¨¡å?—")
public class AdminsProductRecommendController {

    @Reference(validation = "true", version = "${dubbo.provider.ProductRecommendService.version}")
    private ProductRecommendService productRecommendService;
    @Reference(validation = "true", version = "${dubbo.consumer.ProductSpuService.version}")
    private ProductSpuService productSpuService;

    @GetMapping("/page")
    @ApiOperation(value = "å•†å“?æŽ¨è??åˆ†é¡µ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "æŽ¨è??ç±»åž‹", example = "1"),
            @ApiImplicitParam(name = "pageNo", value = "é¡µç ?ï¼Œä»Ž 1 å¼€å§‹", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "æ¯?é¡µæ?¡æ•°", required = true, example = "10"),
    })
    public CommonResult<AdminsProductRecommendPageVO> page(@RequestParam(value = "type", required = false) Integer type,
                                                           @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ProductRecommendPageBO result = productRecommendService.getProductRecommendPage(new ProductRecommendPageDTO().setType(type).setPageNo(pageNo).setPageSize(pageSize));
        // èŽ·å¾—å•†å“?é›†å?ˆ
        List<ProductSpuBO> spus = productSpuService.getProductSpuList(
                result.getList().stream().map(ProductRecommendBO::getProductSpuId).collect(Collectors.toSet()));
        Map<Integer, ProductSpuBO> spuMap = spus.stream().collect(Collectors.toMap(ProductSpuBO::getId, account -> account));
        // æ‹¼è£…ç»“æžœ
        AdminsProductRecommendPageVO response = ProductRecommendConvert.INSTANCE.convert(result);
        response.getList().forEach(recommendVO -> recommendVO.setProductSpuName(spuMap.get(recommendVO.getProductSpuId()).getName()));
        return CommonResult.success(response);
    }

    @PostMapping("/add")
    @ApiOperation(value = "åˆ›å»ºå•†å“?æŽ¨è??")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "æŽ¨è??ç±»åž‹", required = true, example = "1"),
            @ApiImplicitParam(name = "productSpuId", value = "å•†å“?ç¼–å?·", required = true, example = "1"),
            @ApiImplicitParam(name = "sort", value = "æŽ’åº?", required = true, example = "10"),
            @ApiImplicitParam(name = "memo", value = "å¤‡æ³¨", example = "æ´»åŠ¨å¾ˆç‰›é€¼"),
    })
    public CommonResult<AdminsProductRecommendVO> add(@RequestParam("type") Integer type,
                                                      @RequestParam("productSpuId") Integer productSpuId,
                                                      @RequestParam("sort") Integer sort,
                                                      @RequestParam(value = "memo", required = false) String memo) {
        ProductRecommendAddDTO bannerAddDTO = new ProductRecommendAddDTO().setType(type).setProductSpuId(productSpuId)
                .setSort(sort).setMemo(memo);
        return success(ProductRecommendConvert.INSTANCE.convert(productRecommendService.addProductRecommend(AdminSecurityContextHolder.getContext().getAdminId(), bannerAddDTO)));
    }

    @PostMapping("/update")
    @ApiOperation(value = "æ›´æ–°å•†å“?æŽ¨è??")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "å•†å“?æŽ¨è??ç¼–å?·", required = true, example = "1"),
            @ApiImplicitParam(name = "type", value = "æŽ¨è??ç±»åž‹", required = true, example = "1"),
            @ApiImplicitParam(name = "productSpuId", value = "å•†å“?ç¼–å?·", required = true, example = "1"),
            @ApiImplicitParam(name = "sort", value = "æŽ’åº?", required = true, example = "10"),
            @ApiImplicitParam(name = "memo", value = "å¤‡æ³¨", example = "æ´»åŠ¨å¾ˆç‰›é€¼"),
    })
    public CommonResult<Boolean> update(@RequestParam("id") Integer id,
                                        @RequestParam("type") Integer type,
                                        @RequestParam("productSpuId") Integer productSpuId,
                                        @RequestParam("sort") Integer sort,
                                        @RequestParam(value = "memo", required = false) String memo) {
        ProductRecommendUpdateDTO bannerUpdateDTO = new ProductRecommendUpdateDTO().setId(id).setType(type).setProductSpuId(productSpuId)
                .setSort(sort).setMemo(memo);
        return success(productRecommendService.updateProductRecommend(AdminSecurityContextHolder.getContext().getAdminId(), bannerUpdateDTO));
    }

    @PostMapping("/update_status")
    @ApiOperation(value = "æ›´æ–°å•†å“?æŽ¨è??çŠ¶æ€?")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "å•†å“?æŽ¨è??ç¼–å?·", required = true, example = "1"),
            @ApiImplicitParam(name = "status", value = "çŠ¶æ€?ã€‚1 - å¼€å?¯ï¼›2 - ç¦?ç”¨", required = true, example = "1"),
    })
    public CommonResult<Boolean> updateStatus(@RequestParam("id") Integer id,
                                              @RequestParam("status") Integer status) {
        return success(productRecommendService.updateProductRecommendStatus(AdminSecurityContextHolder.getContext().getAdminId(), id, status));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "åˆ é™¤å•†å“?æŽ¨è??")
    @ApiImplicitParam(name = "id", value = "å•†å“?æŽ¨è??ç¼–å?·", required = true, example = "1")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        return success(productRecommendService.deleteProductRecommend(AdminSecurityContextHolder.getContext().getAdminId(), id));
    }

}
