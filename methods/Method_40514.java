/** 
 * Create a copy of the symbol table but without the links to parent, supers and children. Useful for creating instances.
 * @return the symbol table for use by the instance.
 */
public Scope copy(ScopeType tableType){
  Scope ret=new Scope(null,tableType);
  if (table != null) {
    ret.getInternalTable().putAll(table);
  }
  return ret;
}
