@RequiresPermissions("trade:record:view") @RequestMapping(value="/listPaymentRecord",method={RequestMethod.POST,RequestMethod.GET}) public String listPaymentRecord(HttpServletRequest request,PaymentOrderQueryParam paymentOrderQueryParam,PageParam pageParam,Model model){
  PageBean pageBean=rpTradePaymentQueryService.listPaymentRecordPage(pageParam,paymentOrderQueryParam);
  model.addAttribute("pageBean",pageBean);
  model.addAttribute("pageParam",pageParam);
  model.addAttribute("paymentOrderQueryParam",paymentOrderQueryParam);
  model.addAttribute("statusEnums",TradeStatusEnum.toMap());
  model.addAttribute("payWayNameEnums",PayWayEnum.toMap());
  model.addAttribute("payTypeNameEnums",PayTypeEnum.toMap());
  model.addAttribute("fundIntoTypeEnums",FundInfoTypeEnum.toMap());
  model.addAttribute("trxTypeEnums",TrxTypeEnum.toMap());
  return "trade/listPaymentRecord";
}
