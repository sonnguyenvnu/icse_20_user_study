protected List<MappingField> _mfs(Entity<?> en){
  if (null == mfs)   return Pojos.getFieldsForUpdate(_en(en),getFieldMatcher(),refer == null ? pojo.getOperatingObject() : refer);
  return mfs;
}
