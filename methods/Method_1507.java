/** 
 * Sets the data source to having failed with the given exception. <p> This method will return  {@code true} if the exception was successfully set, or{@code false} if the data source has already been set, failed or closed.
 * @param throwable the exception the data source should hold.
 * @return true if the exception was successfully set.
 */
public boolean setException(Throwable throwable){
  return super.setFailure(throwable);
}
