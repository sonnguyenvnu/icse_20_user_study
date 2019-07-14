/** 
 * Returns exception chain starting from top up to root cause.
 */
public static Throwable[] getExceptionChain(Throwable throwable){
  ArrayList<Throwable> list=new ArrayList<>();
  list.add(throwable);
  while ((throwable=throwable.getCause()) != null) {
    list.add(throwable);
  }
  Throwable[] result=new Throwable[list.size()];
  return list.toArray(result);
}
