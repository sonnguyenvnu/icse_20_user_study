private void validateCode(ServletWebRequest servletWebRequest) throws Exception {
  String smsCodeInRequest=ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"smsCode");
  String mobileInRequest=ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"mobile");
  String codeInRedis=redisCodeService.get(servletWebRequest,mobileInRequest);
  if (StringUtils.isBlank(smsCodeInRequest)) {
    throw new Exception("????????");
  }
  if (codeInRedis == null) {
    throw new Exception("???????");
  }
  if (!StringUtils.equalsIgnoreCase(codeInRedis,smsCodeInRequest)) {
    throw new Exception("???????");
  }
  redisCodeService.remove(servletWebRequest,mobileInRequest);
}
