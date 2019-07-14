/** 
 * This method should be called by  {@link org.janusgraph.diskstorage.log.Log} implementations when the message could not be added to the logwith the respective exception object.
 * @param exception
 */
public void failed(Throwable exception){
  super.setException(exception);
}
