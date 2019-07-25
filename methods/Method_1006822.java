private void parse(){
  Type genType=getClass().getGenericSuperclass();
  Type[] params=((ParameterizedType)genType).getActualTypeArguments();
  if (!(params[0] instanceof Class)) {
    return;
  }
  this.clz=(Class)params[0];
  hook();
}
