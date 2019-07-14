/** 
 * Registers table reference for provided entity.
 */
public void registerTableReference(final String tableReference,final DbEntityDescriptor ded,final String tableAlias){
  if (tableRefs == null) {
    tableRefs=new HashMap<>();
  }
  TableRefData t=new TableRefData(ded,tableAlias);
  if (tableRefs.put(tableReference,t) != null) {
    throw new DbSqlBuilderException("Duplicated table reference: " + tableReference);
  }
}
