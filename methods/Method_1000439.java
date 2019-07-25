protected String _fmtcolnm(Entity<?> en,String name){
  if (null == en && null != pojo)   en=pojo.getEntity();
  if (null != en) {
    MappingField mf=en.getField(name);
    if (null != mf)     return mf.getColumnNameInSql();
  }
  return name;
}
