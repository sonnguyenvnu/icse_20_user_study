/** 
 * Throws a  {@link FallthroughException} in order to try to handle the {@link Throwable} by the nexthandler.
 */
static <T>T fallthrough(){
  throw FallthroughException.get();
}
