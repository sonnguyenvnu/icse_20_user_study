package cn.iocoder.mall.order.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.CartService;
import cn.iocoder.mall.order.api.OrderService;
import cn.iocoder.mall.order.api.bo.CalcOrderPriceBO;
import cn.iocoder.mall.order.api.bo.CalcSkuPriceBO;
import cn.iocoder.mall.order.api.bo.CartItemBO;
import cn.iocoder.mall.order.api.dto.CalcOrderPriceDTO;
import cn.iocoder.mall.order.application.convert.CartConvert;
import cn.iocoder.mall.order.application.vo.UsersCalcSkuPriceVO;
import cn.iocoder.mall.order.application.vo.UsersCartDetailVO;
import cn.iocoder.mall.order.application.vo.UsersOrderConfirmCreateVO;
import cn.iocoder.mall.promotion.api.CouponService;
import cn.iocoder.mall.promotion.api.bo.CouponCardAvailableBO;
import cn.iocoder.mall.user.sdk.context.UserSecurityContextHolder;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("users/cart")
public class UsersCartController {

    @Reference(validation = "true", version = "${dubbo.provider.CartService.version}")
    private CartService cartService;

    @Reference(validation = "true", version = "${dubbo.provider.OrderService.version}")
    private OrderService orderService;

    @Reference(validation = "true", version = "${dubbo.consumer.CouponService.version}")
    private CouponService couponService;

    @PostMapping("add")
    public CommonResult<Integer> add(@RequestParam("skuId") Integer skuId,
                                     @RequestParam("quantity") Integer quantity) {
        // 添加到购物车
        cartService.add(UserSecurityContextHolder.getContext().getUserId(), skuId, quantity);
        // 获得目�?购物车商�?总数�?
        return success(cartService.count(UserSecurityContextHolder.getContext().getUserId()));
    }

    @PostMapping("update_quantity")
    public CommonResult<UsersCartDetailVO> updateQuantity(@RequestParam("skuId") Integer skuId, // TODO 芋艿，先暂用这个 VO 。等促销活动出�?��?�，�?�调整
                                                          @RequestParam("quantity") Integer quantity) {
        // 添加到购物车
        cartService.updateQuantity(UserSecurityContextHolder.getContext().getUserId(),
                skuId, quantity);
        // 获得目�?购物车明细
        return getCartDetail();
    }

    @PostMapping("update_selected")
    public CommonResult<UsersCartDetailVO> updateSelected(@RequestParam("skuIds") Set<Integer> skuIds, // TODO 芋艿，先暂用这个 VO 。等促销活动出�?��?�，�?�调整
                                                          @RequestParam("selected") Boolean selected) {
        // 添加到购物车
        cartService.updateSelected(UserSecurityContextHolder.getContext().getUserId(), skuIds, selected);
        // 获得目�?购物车明细
        return getCartDetail();
    }

    @GetMapping("count")
    public CommonResult<Integer> count() {
        return success(cartService.count(UserSecurityContextHolder.getContext().getUserId()));
    }

    @GetMapping("/list")
    public CommonResult<UsersCartDetailVO> list() { // TODO 芋艿，先暂用这个 VO 。等促销活动出�?��?�，�?�调整
        return getCartDetail();
    }

    private CommonResult<UsersCartDetailVO> getCartDetail() {
        // 获得购物车中选中的
        List<CartItemBO> cartItems = cartService.list(UserSecurityContextHolder.getContext().getUserId(), null);
        // 购物车为空时，构造空的 UsersOrderConfirmCreateVO 返回
        if (cartItems.isEmpty()) {
            UsersCartDetailVO result = new UsersCartDetailVO();
            result.setItemGroups(Collections.emptyList());
            result.setFee(new UsersCartDetailVO.Fee(0, 0, 0, 0));
            return success(result);
        }
        // 计算商�?价格
        CalcOrderPriceBO calcOrder = list0(cartItems, null);
        // 执行数�?�拼装
        return success(CartConvert.INSTANCE.convert2(calcOrder));
    }

    @GetMapping("/confirm_create_order")
    public CommonResult<UsersOrderConfirmCreateVO> getConfirmCreateOrder(@RequestParam(value = "couponCardId", required = false) Integer couponCardId) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        // 获得购物车中选中的
        List<CartItemBO> cartItems = cartService.list(userId, true);
        // 购物车为空时，构造空的 UsersOrderConfirmCreateVO 返回
        if (cartItems.isEmpty()) {
            UsersOrderConfirmCreateVO result = new UsersOrderConfirmCreateVO();
            result.setItemGroups(Collections.emptyList());
            result.setFee(new UsersOrderConfirmCreateVO.Fee(0, 0, 0, 0));
            return success(result);
        }
        // 计算商�?价格
        CalcOrderPriceBO calcOrderPrice = list0(cartItems, couponCardId);
        // 获得优惠劵
        List<CouponCardAvailableBO> couponCards = couponService.getCouponCardList(userId,
                CartConvert.INSTANCE.convertList(calcOrderPrice.getItemGroups()));
        // 执行数�?�拼装
        return success(CartConvert.INSTANCE.convert(calcOrderPrice).setCouponCards(couponCards));
    }

    private CalcOrderPriceBO list0(List<CartItemBO> cartItems, Integer couponCardId) {
        // 创建计算的 DTO
        CalcOrderPriceDTO calcOrderPriceDTO = new CalcOrderPriceDTO()
                .setUserId(UserSecurityContextHolder.getContext().getUserId())
                .setItems(new ArrayList<>(cartItems.size()))
                .setCouponCardId(couponCardId);
        for (CartItemBO item : cartItems) {
            calcOrderPriceDTO.getItems().add(new CalcOrderPriceDTO.Item(item.getSkuId(), item.getQuantity(), item.getSelected()));
        }
        // 执行计算
        return cartService.calcOrderPrice(calcOrderPriceDTO);
    }

    @GetMapping("/calc_sku_price")
    public CommonResult<UsersCalcSkuPriceVO> calcSkuPrice(@RequestParam("skuId") Integer skuId) {
        // 计算 sku 的价格
        CalcSkuPriceBO calcSkuPrice = cartService.calcSkuPrice(skuId);
        return success(CartConvert.INSTANCE.convert2(calcSkuPrice));
    }

    public CommonResult<Object> confirmOrder() {
        // 查询购物车列表（选中的）
//        cartService.list(userId, true);
        // 查询确认订�?�信�?�的明细

        return null;
    }

}
