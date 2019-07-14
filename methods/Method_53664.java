@RequestMapping(value="/pay/edit",method=RequestMethod.POST) @ApiOperation(value="??????") @ResponseBody public Result<Object> editPay(@ModelAttribute Pay pay,@RequestParam(required=true) String id,@RequestParam(required=true) String token){
  String temp=redisUtils.get(id);
  if (!token.equals(temp)) {
    return new ResultUtil<Object>().setErrorMsg("???Token???");
  }
  try {
    pay.setId(getPayId(pay.getId()));
    Pay p=payService.getPay(getPayId(pay.getId()));
    pay.setState(p.getState());
    if (!pay.getId().contains(FAKE_PRE)) {
      pay.setCreateTime(StringUtils.getDate(pay.getTime()));
    }
 else {
      pay.setMoney(p.getMoney());
      pay.setPayType(p.getPayType());
    }
    payService.updatePay(pay);
  }
 catch (  Exception e) {
    return new ResultUtil<Object>().setErrorMsg("????????");
  }
  if (id.contains(FAKE_PRE)) {
    redisUtils.set(id,"",1L,TimeUnit.SECONDS);
  }
  return new ResultUtil<Object>().setData(null);
}
