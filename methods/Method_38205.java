/** 
 * Returns the first property name of column marked as identity. Throws an exception if table has composite primary key.
 */
public String getIdPropertyName(){
  ensureSingleIdColumn();
  return idColumnDescriptors[0].getPropertyName();
}
