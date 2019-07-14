@Override public boolean shouldRetryAuthentication(Response response){
  ResponseBody responseBody;
  try {
    responseBody=response.peekBody(Long.MAX_VALUE);
  }
 catch (  IOException e) {
    e.printStackTrace();
    return false;
  }
  ApiError apiError=new ApiError(response,responseBody);
switch (apiError.code) {
case ApiContract.Response.Error.Codes.Token.INVALID_ACCESS_TOKEN:
case ApiContract.Response.Error.Codes.Token.ACCESS_TOKEN_HAS_EXPIRED:
case ApiContract.Response.Error.Codes.Token.INVALID_REFRESH_TOKEN:
case ApiContract.Response.Error.Codes.Token.ACCESS_TOKEN_HAS_EXPIRED_SINCE_PASSWORD_CHANGED:
    return true;
default :
  return false;
}
}
