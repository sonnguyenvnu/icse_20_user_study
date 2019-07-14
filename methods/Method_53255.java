/** 
 * Tests if the exception is caused by non-existing resource
 * @return if the exception is caused by non-existing resource
 * @since Twitter4J 2.1.2
 */
public boolean resourceNotFound(){
  return statusCode == NOT_FOUND;
}
