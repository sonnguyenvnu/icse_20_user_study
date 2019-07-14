@Override public void process(final StringBuilder out){
  if (isPreviousChunkOfType(CHUNK_TABLE)) {
    appendMissingSpace(out);
  }
  DbEntityDescriptor ded=tableRef != null ? lookupTableRef(tableRef) : lookupType(resolveClass(data));
  out.append(SET);
  DbEntityColumnDescriptor[] decList=ded.getColumnDescriptors();
  String typeName=StringUtil.uncapitalize(ded.getEntityName());
  int size=0;
  for (  DbEntityColumnDescriptor dec : decList) {
    if (dec.isId() && !isUpdateablePrimaryKey) {
      continue;
    }
    String property=dec.getPropertyName();
    Object value=BeanUtil.declared.getProperty(data,property);
    if (includeColumns == COLS_ONLY_EXISTING) {
      if (isEmptyColumnValue(dec,value)) {
        continue;
      }
    }
    if (size > 0) {
      out.append(',').append(' ');
    }
    size++;
    out.append(dec.getColumnNameForQuery()).append('=');
    String propertyName=typeName + '.' + property;
    defineParameter(out,propertyName,value,dec);
  }
  if (size > 0) {
    out.append(' ');
  }
}
