/** 
 * Clear cached value computations for all columns
 */
public void clearPrecomputes(){
  for (  Column column : columns) {
    column.clearPrecomputes();
  }
}
