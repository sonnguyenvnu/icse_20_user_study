private void validateCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
  String smsCodeInRequest=ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"smsCode");
  String mobileInRequest=ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"smsCode");
  SmsCode codeInSession=(SmsCode)sessionStrategy.getAttribute(servletWebRequest,ValidateController.SESSION_KEY_SMS_CODE + mobileInRequest);
  if (StringUtils.isBlank(smsCodeInRequest)) {
    throw new ValidateCodeException("????????");
  }
  if (codeInSession == null) {
    throw new ValidateCodeException("???????");
  }
  if (codeInSession.isExpire()) {
    sessionStrategy.removeAttribute(servletWebRequest,ValidateController.SESSION_KEY_IMAGE_CODE);
    throw new ValidateCodeException("???????");
  }
  if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(),smsCodeInRequest)) {
    throw new ValidateCodeException("???????");
  }
  sessionStrategy.removeAttribute(servletWebRequest,ValidateController.SESSION_KEY_IMAGE_CODE);
}
