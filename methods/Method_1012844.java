public static <T>String success(String message){
  return toString(new ResultBody().setMessage(message));
}
