@RequiresPermissions("trade:order:view") @RequestMapping(value="/listPaymentOrder",method={RequestMethod.POST,RequestMethod.GET}) public String listPaymentOrder(HttpServletRequest request,PaymentOrderQueryParam paymentOrderQueryParam,PageParam pageParam,Model model){
  PageBean pageBean=rpTradePaymentQueryService.listPaymentOrderPage(pageParam,paymentOrderQueryParam);
  model.addAttribute("pageBean",pageBean);
  model.addAttribute("pageParam",pageParam);
  model.addAttribute("paymentOrderQueryParam",paymentOrderQueryParam);
  model.addAttribute("statusEnums",TradeStatusEnum.toMap());
  model.addAttribute("payWayNameEnums",PayWayEnum.toMap());
  model.addAttribute("payTypeNameEnums",PayTypeEnum.toMap());
  model.addAttribute("fundIntoTypeEnums",FundInfoTypeEnum.toMap());
  return "trade/listPaymentOrder";
}
