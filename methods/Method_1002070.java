private boolean check(HttpServletRequest request,HttpServletResponse response){
  if (request.getServletPath().equals(JwtConstants.AUTH_PATH)) {
    return true;
  }
  final String requestHeader=request.getHeader(JwtConstants.AUTH_HEADER);
  String authToken;
  if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
    authToken=requestHeader.substring(7);
    try {
      boolean flag=JwtTokenUtil.isTokenExpired(authToken);
      if (flag) {
        RenderUtil.renderJson(response,new ErrorResponseData(BizExceptionEnum.TOKEN_EXPIRED.getCode(),BizExceptionEnum.TOKEN_EXPIRED.getMessage()));
        return false;
      }
    }
 catch (    JwtException e) {
      RenderUtil.renderJson(response,new ErrorResponseData(BizExceptionEnum.TOKEN_ERROR.getCode(),BizExceptionEnum.TOKEN_ERROR.getMessage()));
      return false;
    }
  }
 else {
    RenderUtil.renderJson(response,new ErrorResponseData(BizExceptionEnum.TOKEN_ERROR.getCode(),BizExceptionEnum.TOKEN_ERROR.getMessage()));
    return false;
  }
  return true;
}
