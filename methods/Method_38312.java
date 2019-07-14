/** 
 * Specifies column alias type. May be <code>null</code> when column aliases are not used.
 */
public DbSqlBuilder aliasColumnsAs(final ColumnAliasType aliasesType){
  this.columnAliasType=aliasesType;
  return this;
}
