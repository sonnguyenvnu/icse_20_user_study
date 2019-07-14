@Override public void process(final StringBuilder out){
  if (objectRef != null) {
    data=templateData.lookupObject(objectRef);
  }
  DbEntityDescriptor ded=tableRef != null ? lookupTableRef(tableRef) : lookupType(resolveClass(data));
  String table=resolveTable(tableRef,ded);
  DbEntityColumnDescriptor[] decList=ded.getColumnDescriptors();
  String typeName=StringUtil.uncapitalize(ded.getEntityName());
  int count=0;
  out.append('(');
  for (  DbEntityColumnDescriptor dec : decList) {
    if ((includeColumns == COLS_ONLY_IDS) && (!dec.isId())) {
      continue;
    }
    String property=dec.getPropertyName();
    Object value=BeanUtil.declaredSilent.getProperty(data,property);
    if ((includeColumns == COLS_ONLY_EXISTING) && (value == null)) {
      continue;
    }
    if (includeColumns == COLS_ONLY_EXISTING) {
      if (isEmptyColumnValue(dec,value)) {
        continue;
      }
    }
    if (count > 0) {
      out.append(AND);
    }
    count++;
    out.append(table).append('.').append(dec.getColumnNameForQuery()).append('=');
    String propertyName=objectRef != null ? objectRef : typeName;
    propertyName+='.' + property;
    defineParameter(out,propertyName,value,dec);
  }
  if (count == 0) {
    out.append(DEFAULT);
  }
  out.append(')');
}
