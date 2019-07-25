/** 
 * Tries to locate the RxJavaAssemblyException in the chain of causes of the given Throwable.
 * @param ex the Throwable to start scanning
 * @return the RxJavaAssemblyException found or null
 */
public static RxJavaAssemblyException find(Throwable ex){
  Set<Throwable> memory=new HashSet<Throwable>();
  while (ex != null) {
    if (ex instanceof RxJavaAssemblyException) {
      return (RxJavaAssemblyException)ex;
    }
    if (memory.add(ex)) {
      ex=ex.getCause();
    }
 else {
      return null;
    }
  }
  return null;
}
