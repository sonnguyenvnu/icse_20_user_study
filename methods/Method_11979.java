/** 
 * Sorts the tests in <code>target</code> using <code>comparator</code>.
 * @since 4.0
 */
@Override public void apply(Object target){
  if (target instanceof Sortable) {
    Sortable sortable=(Sortable)target;
    sortable.sort(this);
  }
}
