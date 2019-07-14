/** 
 * Invoke with a  {@link org.junit.runner.Runner} to cause all tests it intends to runto first be checked with the filter. Only those that pass the filter will be run.
 * @param child the runner to be filtered by the receiver
 * @throws NoTestsRemainException if the receiver removes all tests
 */
public void apply(Object child) throws NoTestsRemainException {
  if (!(child instanceof Filterable)) {
    return;
  }
  Filterable filterable=(Filterable)child;
  filterable.filter(this);
}
