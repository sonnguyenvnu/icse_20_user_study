@StringRes private int getPrettifiedErrorMessage(@Nullable Throwable throwable){
  int resId=R.string.network_error;
  if (throwable instanceof HttpException) {
    resId=R.string.network_error;
  }
 else   if (throwable instanceof IOException) {
    resId=R.string.request_error;
  }
 else   if (throwable instanceof TimeoutException) {
    resId=R.string.unexpected_error;
  }
  return resId;
}
