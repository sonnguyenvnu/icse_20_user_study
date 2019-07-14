private String key(ServletWebRequest request,String mobile) throws Exception {
  String deviceId=request.getHeader("deviceId");
  if (StringUtils.isBlank(deviceId)) {
    throw new Exception("????????deviceId");
  }
  return SMS_CODE_PREFIX + deviceId + ":" + mobile;
}
