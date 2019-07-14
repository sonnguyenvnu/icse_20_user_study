/** 
 * orderId??? -1????? 0? ???
 */
@AccessLimit(seconds=5,maxCount=5,needLogin=true) @RequestMapping(value="/result",method=RequestMethod.GET) @ResponseBody public ResultGeekQ<Long> miaoshaResult(Model model,MiaoshaUser user,@RequestParam("goodsId") long goodsId){
  ResultGeekQ<Long> result=ResultGeekQ.build();
  if (user == null) {
    result.withError(SESSION_ERROR.getCode(),SESSION_ERROR.getMessage());
    return result;
  }
  model.addAttribute("user",user);
  Long miaoshaResult=miaoshaService.getMiaoshaResult(Long.valueOf(user.getNickname()),goodsId);
  result.setData(miaoshaResult);
  return result;
}
