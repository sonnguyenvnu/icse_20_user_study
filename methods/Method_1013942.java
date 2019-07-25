@Override public Map<String,Object> execute(Map<String,Object> inputs){
  Object message=getMessage(inputs);
  if (message == null) {
    message="";
  }
  return null;
}
