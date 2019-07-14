/** 
 * Order the tests in <code>target</code>.
 * @throws InvalidOrderingException if ordering does something invalid (like remove or addchildren)
 */
public void apply(Object target) throws InvalidOrderingException {
  if (target instanceof Orderable) {
    Orderable orderable=(Orderable)target;
    orderable.order(this);
  }
}
