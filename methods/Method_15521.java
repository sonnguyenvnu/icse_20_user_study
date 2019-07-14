@NotNull @Override public RequestMethod getMethod(){
  if (method == null) {
    method=GET;
  }
  return method;
}
