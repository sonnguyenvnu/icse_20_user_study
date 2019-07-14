public static <T>ResultGeekQOrder<T> build(String message){
  return new ResultGeekQOrder(ResultStatusOrder.SUCCESS,message);
}
