/** 
 * Ensures that any buffered additions are flushed to the underlying bitmap.
 */
@Override public void flush(){
  currentKey+=appendToUnderlying();
}
