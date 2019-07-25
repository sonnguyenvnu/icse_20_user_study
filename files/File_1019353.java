package cn.iocoder.mall.promotion.application.controller.users;

import cn.iocoder.common.framework.constant.CommonStatusEnum;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.ProductSpuService;
import cn.iocoder.mall.product.api.bo.ProductSpuBO;
import cn.iocoder.mall.promotion.api.ProductRecommendService;
import cn.iocoder.mall.promotion.api.bo.ProductRecommendBO;
import cn.iocoder.mall.promotion.application.convert.ProductRecommendConvert;
import cn.iocoder.mall.promotion.application.vo.users.UsersProductRecommendVO;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users/product_recommend")
@Api("商�?推�??模�?�")
public class UsersProductRecommendController {

    @Reference(validation = "true", version = "${dubbo.provider.ProductRecommendService.version}")
    private ProductRecommendService productRecommendService;
    @Reference(validation = "true", version = "${dubbo.consumer.ProductSpuService.version}")
    private ProductSpuService productSpuService;

    @GetMapping("/list")
    @ApiOperation("获得所有 Banner 列表")
    public CommonResult<Map<Integer, Collection<UsersProductRecommendVO>>> list() {
        // 查询商�?推�??列表
        List<ProductRecommendBO> productRecommends = productRecommendService.getProductRecommendList(
                null, CommonStatusEnum.ENABLE.getValue());
        // 获得商�?集�?�
        List<ProductSpuBO> spus = productSpuService.getProductSpuList(
                productRecommends.stream().map(ProductRecommendBO::getProductSpuId).collect(Collectors.toSet()));
        Map<Integer, ProductSpuBO> spuMap = spus.stream().collect(Collectors.toMap(ProductSpuBO::getId, account -> account));
        // 组�?�结果，返回
        Multimap<Integer, UsersProductRecommendVO> result = HashMultimap.create();
        productRecommends.sort(Comparator.comparing(ProductRecommendBO::getSort)); // 排�?，按照 sort �?��?
        productRecommends.forEach(productRecommendBO -> result.put(productRecommendBO.getType(),
                ProductRecommendConvert.INSTANCE.convert(spuMap.get(productRecommendBO.getProductSpuId())))); // 将 ProductSpuBO 添加到 results 中
        return CommonResult.success(result.asMap());
    }

}
