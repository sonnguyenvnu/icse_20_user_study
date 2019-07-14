@Override public <T>T getIndexProperty(final Object bean,final String property,final int index){
  BeanProperty bp=new BeanProperty(this,bean,property);
  bp.indexString=bp.index=String.valueOf(index);
  Object value=_getIndexProperty(bp);
  bp.indexString=null;
  return (T)value;
}
