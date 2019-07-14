protected OAuth2Response createResponse(Supplier<Response> responseSupplier){
  createNativeResponse(responseSupplier);
  if (null != expiredCallBack) {
    auth2Response.judgeError(ErrorType.EXPIRED_TOKEN,() -> {
      expiredCallBack.call(() -> createNativeResponse(responseSupplier));
      return auth2Response;
    }
);
  }
  if (null != refreshTokenExpiredCallBack) {
    auth2Response.judgeError(ErrorType.INVALID_TOKEN,() -> {
      refreshTokenExpiredCallBack.call(() -> createNativeResponse(responseSupplier));
      return auth2Response;
    }
);
    auth2Response.judgeError(ErrorType.EXPIRED_REFRESH_TOKEN,() -> {
      refreshTokenExpiredCallBack.call(() -> createNativeResponse(responseSupplier));
      return auth2Response;
    }
);
    auth2Response.judgeError(ErrorType.INVALID_TOKEN,() -> {
      refreshTokenExpiredCallBack.call(() -> createNativeResponse(responseSupplier));
      return auth2Response;
    }
);
  }
  return auth2Response;
}
