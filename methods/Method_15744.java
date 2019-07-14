@Override public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
  if (handler instanceof HandlerMethod) {
    HandlerMethod method=((HandlerMethod)handler);
    TwoFactor factor=method.getMethodAnnotation(TwoFactor.class);
    if (factor == null || factor.ignore()) {
      return true;
    }
    String userId=Authentication.current().map(Authentication::getUser).map(User::getId).orElse(null);
    TwoFactorValidator validator=validatorManager.getValidator(userId,factor.value(),factor.provider());
    if (!validator.expired()) {
      return true;
    }
    String code=request.getParameter(factor.parameter());
    if (code == null) {
      code=request.getHeader(factor.parameter());
    }
    if (StringUtils.isEmpty(code)) {
      throw new NeedTwoFactorException(factor.message(),factor.provider());
    }
 else     if (!validator.verify(code,factor.timeout())) {
      throw new NeedTwoFactorException("?????",factor.provider());
    }
  }
  return super.preHandle(request,response,handler);
}
