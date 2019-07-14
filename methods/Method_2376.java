@RequestMapping("/notifyUrl") @ResponseBody public Object notifyUrl(HttpServletRequest request) throws Exception {
  Map<String,String> parameterMap=RequestUtil.getParameterMap(request);
  boolean signVerified=AlipaySignature.rsaCheckV1(parameterMap,PropertiesFileUtil.getInstance().get("alipay.alipay_public_key"),PropertiesFileUtil.getInstance().get("alipay.charset"),PropertiesFileUtil.getInstance().get("alipay.sign_type"));
  if (!signVerified) {
    return PayConstant.FAILED;
  }
  return PayConstant.SUCCESS;
}
