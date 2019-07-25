public void append(String key,String message,Object... values){
  checkNotNull(message,"values");
  checkNotNull(values,"values");
  append(key,String.format(message,values));
}
