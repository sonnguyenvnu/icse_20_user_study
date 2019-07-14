/** 
 * {@inheritDoc}.
 */
@Override boolean isClosureCommand(Object closureObj){
  return closureObj instanceof AsyncResult;
}
