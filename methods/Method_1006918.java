/** 
 * Register an anonymous skip. To skip an individual item, use {@link ChunkIterator#remove()}.
 * @param e the exception that caused the skip
 */
public void skip(Exception e){
  errors.add(e);
}
