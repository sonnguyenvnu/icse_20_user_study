/** 
 * Returns a reference to the type of the exception declared in a 'catch' clause of a method.
 * @param tryCatchBlockIndex the index of a try catch block (using the order in which they arevisited with visitTryCatchBlock).
 * @return a reference to the type of the given exception.
 */
public static TypeReference newTryCatchReference(final int tryCatchBlockIndex){
  return new TypeReference((EXCEPTION_PARAMETER << 24) | (tryCatchBlockIndex << 8));
}
