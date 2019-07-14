/** 
 * Retrieves the underlying error when  {@link #type} is {@link #TYPE_RENDERER}.
 * @throws IllegalStateException If {@link #type} is not {@link #TYPE_RENDERER}.
 */
public Exception getRendererException(){
  Assertions.checkState(type == TYPE_RENDERER);
  return (Exception)cause;
}
