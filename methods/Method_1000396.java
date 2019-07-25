public void visit(Object obj,final LinkField lnk){
  Object value=lnk.getValue(obj);
  if (Lang.eleSize(value) == 0)   return;
  if (value instanceof Map<?,?>)   value=((Map<?,?>)value).values();
  FieldMatcher fm=FieldFilter.get(lnk.getLinkedEntity().getType());
  if (null != fm && fm.isIgnoreNull()) {
    opt.addUpdateForIgnoreNull(lnk.getLinkedEntity(),value,fm);
  }
 else {
    opt.addUpdate(lnk.getLinkedEntity(),value);
  }
}
