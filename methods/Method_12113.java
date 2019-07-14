@RequestMapping(value="/verifyCodeRegister",method=RequestMethod.GET) @ResponseBody public ResultGeekQ<String> getMiaoshaVerifyCod(HttpServletResponse response){
  ResultGeekQ<String> result=ResultGeekQ.build();
  try {
    BufferedImage image=miaoshaService.createVerifyCodeRegister();
    OutputStream out=response.getOutputStream();
    ImageIO.write(image,"JPEG",out);
    out.flush();
    out.close();
    return result;
  }
 catch (  Exception e) {
    logger.error("???????-----??:{}",e);
    result.withError(MIAOSHA_FAIL.getCode(),MIAOSHA_FAIL.getMessage());
    return result;
  }
}
