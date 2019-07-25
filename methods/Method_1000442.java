protected List<MappingField> _mfs(Entity<?> en){
  if (null == mfs)   return Pojos.getFieldsForInsert(_en(en),getFieldMatcher());
  return mfs;
}
