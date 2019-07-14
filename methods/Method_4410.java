/** 
 * Retrieves the underlying error when  {@link #type} is {@link #TYPE_UNEXPECTED}.
 * @throws IllegalStateException If {@link #type} is not {@link #TYPE_UNEXPECTED}.
 */
public RuntimeException getUnexpectedException(){
  Assertions.checkState(type == TYPE_UNEXPECTED);
  return (RuntimeException)cause;
}
