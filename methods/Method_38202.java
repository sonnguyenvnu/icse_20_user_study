/** 
 * Returns total number of identity columns.
 */
public int getIdColumnsCount(){
  init();
  return idColumnDescriptors == null ? 0 : idColumnDescriptors.length;
}
