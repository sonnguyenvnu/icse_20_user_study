public void append(String key,String message){
  checkNotNull(key,"key");
  lines.add(new Line(key,message));
}
