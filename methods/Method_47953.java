@Override public void putString(String key,String value){
  props.setProperty(key,value);
  flush();
}
