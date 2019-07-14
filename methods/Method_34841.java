/** 
 * Throws the argument, return-type is RuntimeException so the caller can use a throw statement break out of the method
 */
public static RuntimeException sneakyThrow(Throwable t){
  return Exceptions.<RuntimeException>doThrow(t);
}
