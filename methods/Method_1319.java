/** 
 * Gets a  {@code Throwable} cause chain as a list.  The first entry in thelist will be  {@code throwable} followed by its cause hierarchy.  Notethat this is a snapshot of the cause chain and will not reflect any subsequent changes to the cause chain. <p>Here's an example of how it can be used to find specific types of exceptions in the cause chain: <pre> Iterables.filter(Throwables.getCausalChain(e), IOException.class)); </pre>
 * @param throwable the non-null {@code Throwable} to extract causes from
 * @return an unmodifiable list containing the cause chain starting with{@code throwable}
 */
public static List<Throwable> getCausalChain(Throwable throwable){
  Preconditions.checkNotNull(throwable);
  List<Throwable> causes=new ArrayList<Throwable>(4);
  while (throwable != null) {
    causes.add(throwable);
    throwable=throwable.getCause();
  }
  return Collections.unmodifiableList(causes);
}
