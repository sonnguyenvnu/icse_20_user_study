@Override public void clear(){
  for (  String key : props.stringPropertyNames())   props.remove(key);
  flush();
}
