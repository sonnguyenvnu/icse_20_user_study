private void validateCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
  ImageCode codeInSession=(ImageCode)sessionStrategy.getAttribute(servletWebRequest,ValidateController.SESSION_KEY_IMAGE_CODE);
  String codeInRequest=ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"imageCode");
  if (StringUtils.isBlank(codeInRequest)) {
    throw new ValidateCodeException("????????");
  }
  if (codeInSession == null) {
    throw new ValidateCodeException("???????");
  }
  if (codeInSession.isExpire()) {
    sessionStrategy.removeAttribute(servletWebRequest,ValidateController.SESSION_KEY_IMAGE_CODE);
    throw new ValidateCodeException("???????");
  }
  if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(),codeInRequest)) {
    throw new ValidateCodeException("???????");
  }
  sessionStrategy.removeAttribute(servletWebRequest,ValidateController.SESSION_KEY_IMAGE_CODE);
}
