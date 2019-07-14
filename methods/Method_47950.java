@Override public void putBoolean(String key,boolean value){
  props.setProperty(key,Boolean.toString(value));
}
