@Override public void process(final StringBuilder out){
  final DbEntityDescriptor ded;
  if (tableRef != null) {
    ded=lookupTableRef(tableRef);
    final String tableName=resolveTable(tableRef,ded);
    out.append(tableName);
  }
 else {
    ded=findColumnRef(columnRef);
  }
  if (onlyId) {
    if (tableRef != null) {
      out.append('.');
    }
    out.append(ded.getIdColumnName());
  }
 else   if (columnRef != null) {
    DbEntityColumnDescriptor dec=ded.findByPropertyName(columnRef);
    templateData.lastColumnDec=dec;
    if (dec == null) {
      throw new DbSqlBuilderException("Invalid column reference: [" + tableRef + '.' + columnRef + "]");
    }
    if (tableRef != null) {
      out.append('.');
    }
    out.append(dec.getColumnNameForQuery());
  }
}
