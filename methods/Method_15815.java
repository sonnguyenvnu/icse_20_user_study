public void judgeError(ErrorType ifError,Supplier<OAuth2Response> expiredCallBack){
  if (errorType == ifError) {
    OAuth2Response retryRes=expiredCallBack.get();
    if (retryRes == null) {
      return;
    }
    proxy=retryRes;
    proxy.onError((retryResponse,type) -> {
      if (type == ifError) {
        logger.error("still error [{}], maybe judge error or auth server error? {}",ifError,retryResponse,Thread.currentThread().getStackTrace());
      }
 else {
        errorType=type;
      }
    }
);
    data=UnCheck.unCheck(proxy::asBytes);
    status=proxy.status();
  }
}
