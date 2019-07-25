@GetMapping("list") public CommonResult<OrderReturnListBO> list(@Validated OrderReturnQueryPO queryPO){
  OrderReturnQueryDTO queryDTO=OrderReturnConvert.INSTANCE.convert(queryPO);
  return orderReturnService.orderReturnList(queryDTO);
}
