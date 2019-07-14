/** 
 * Defines object reference and an object.
 */
public DbSqlBuilder use(final String name,final Object value){
  setObjectReference(name,value);
  return this;
}
