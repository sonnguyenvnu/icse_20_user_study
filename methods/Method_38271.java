@Override public void process(final StringBuilder out){
  if (templateData.hasHints()) {
    templateData.registerHint(hint == null ? tableRef : hint);
  }
  separateByCommaOrSpace(out);
  if (tableRef.length() == 0) {
    out.append(columnRef);
    return;
  }
  boolean useTableReference=true;
  DbEntityDescriptor ded=lookupTableRef(tableRef,false);
  if (ded == null) {
    useTableReference=false;
    ded=lookupName(tableRef);
  }
  if (columnRef == null) {
    DbEntityColumnDescriptor[] decList=ded.getColumnDescriptors();
    int count=0;
    boolean withIds=(columnRefArr != null) && ArraysUtil.contains(columnRefArr,StringPool.PLUS);
    for (    DbEntityColumnDescriptor dec : decList) {
      if ((includeColumns == COLS_ONLY_IDS) && (!dec.isId())) {
        continue;
      }
      if ((includeColumns == COLS_ALL_BUT_ID) && (dec.isId())) {
        continue;
      }
      if ((includeColumns == COLS_NA_MULTI) && (!withIds || (!dec.isId())) && (!ArraysUtil.contains(columnRefArr,dec.getPropertyName()))) {
        continue;
      }
      if (count > 0) {
        out.append(',').append(' ');
      }
      templateData.lastColumnDec=dec;
      if (useTableReference) {
        appendColumnName(out,ded,dec);
      }
 else {
        appendAlias(out,ded,dec);
      }
      count++;
    }
  }
 else {
    final DbEntityColumnDescriptor dec=ded.findByPropertyName(columnRef);
    if (dec == null) {
      throw new DbSqlBuilderException("Invalid column reference: [" + tableRef + '.' + columnRef + "]");
    }
    templateData.lastColumnDec=dec;
    if (useTableReference) {
      appendColumnName(out,ded,dec);
    }
 else {
      appendAlias(out,ded,dec);
    }
  }
}
