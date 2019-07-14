private static String appendThrowableMessage(String message,@Nullable Throwable throwable){
  if (throwable == null) {
    return message;
  }
  String throwableMessage=throwable.getMessage();
  return TextUtils.isEmpty(throwableMessage) ? message : message + " - " + throwableMessage;
}
