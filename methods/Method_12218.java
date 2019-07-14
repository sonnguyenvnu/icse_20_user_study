@RequestMapping(value="/verifyCode",method=RequestMethod.GET) @ResponseBody public ResultGeekQ<String> getMiaoshaVerifyCod(HttpServletResponse response,MiaoshaUser user,@RequestParam("goodsId") long goodsId){
  ResultGeekQ<String> result=ResultGeekQ.build();
  if (user == null) {
    result.withError(SESSION_ERROR.getCode(),SESSION_ERROR.getMessage());
    return result;
  }
  try {
    BufferedImage image=miaoshaService.createVerifyCode(user,goodsId);
    OutputStream out=response.getOutputStream();
    ImageIO.write(image,"JPEG",out);
    out.flush();
    out.close();
    return result;
  }
 catch (  Exception e) {
    logger.error("???????-----goodsId:{}",goodsId,e);
    result.withError(MIAOSHA_FAIL.getCode(),MIAOSHA_FAIL.getMessage());
    return result;
  }
}
