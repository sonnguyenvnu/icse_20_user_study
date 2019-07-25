public void reset(Class<?> clz){
  _class=clz;
  _className=clz.getName();
  _hashCode=_className.hashCode();
}
