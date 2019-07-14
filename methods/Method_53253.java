/** 
 * Tests if the exception is caused by network issue
 * @return if the exception is caused by network issue
 * @since Twitter4J 2.1.2
 */
public boolean isCausedByNetworkIssue(){
  return getCause() instanceof java.io.IOException;
}
