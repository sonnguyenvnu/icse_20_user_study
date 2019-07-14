@RequestMapping(value="/pay/pass",method=RequestMethod.GET) @ApiOperation(value="????????") public String addPay(@RequestParam(required=true) String id,@RequestParam(required=true) String token,@RequestParam(required=true) String myToken,Model model){
  String temp=redisUtils.get(id);
  if (!token.equals(temp)) {
    model.addAttribute("errorMsg","???Token???");
    return "/500";
  }
  if (!myToken.equals(MY_TOKEN)) {
    model.addAttribute("errorMsg","?????????????");
    return "/500";
  }
  try {
    payService.changePayState(getPayId(id),1);
    Pay pay=payService.getPay(getPayId(id));
    if (StringUtils.isNotBlank(pay.getEmail()) && EmailUtils.checkEmail(pay.getEmail())) {
      emailUtils.sendTemplateMail(EMAIL_SENDER,pay.getEmail(),"?XPay???????????????","pay-success",pay);
    }
  }
 catch (  Exception e) {
    model.addAttribute("errorMsg","??????");
    return "/500";
  }
  return "redirect:/success";
}
