/** 
 * Generates unique id for RequestFuture.
 * @return unique id
 */
public String generateUniqueFutureId(){
  return String.valueOf(mIdCounter.getAndIncrement());
}
