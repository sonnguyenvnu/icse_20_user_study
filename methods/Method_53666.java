@RequestMapping(value="/pay/passNotShow",method=RequestMethod.GET) @ApiOperation(value="?????????????") public String passNotShowPay(@RequestParam(required=true) String id,@RequestParam(required=true) String token,Model model){
  String temp=redisUtils.get(id);
  if (!token.equals(temp)) {
    model.addAttribute("errorMsg","???Token???");
    return "/500";
  }
  try {
    payService.changePayState(getPayId(id),3);
    Pay pay=payService.getPay(getPayId(id));
    if (StringUtils.isNotBlank(pay.getEmail()) && EmailUtils.checkEmail(pay.getEmail())) {
      emailUtils.sendTemplateMail(EMAIL_SENDER,pay.getEmail(),"?XPay???????????????","pay-notshow",pay);
    }
  }
 catch (  Exception e) {
    model.addAttribute("errorMsg","??????");
    return "/500";
  }
  if (id.contains(FAKE_PRE)) {
    redisUtils.set(id,"",1L,TimeUnit.SECONDS);
  }
  return "redirect:/success";
}
