@Override public void putLong(String key,long value){
  props.setProperty(key,Long.toString(value));
  flush();
}
