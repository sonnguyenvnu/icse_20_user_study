@Override public void putInt(String key,int value){
  props.setProperty(key,Integer.toString(value));
  flush();
}
