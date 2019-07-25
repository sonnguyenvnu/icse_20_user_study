/** 
 * Joins together the results from several patterns.
 * @param plans the array of plans with the common result type
 * @param < R > the result type
 * @return the Observable coining the plans
 */
public static <R>Observable<R> when(Plan<R>... plans){
  if (plans == null) {
    throw new NullPointerException("plans");
  }
  return when(Arrays.asList(plans));
}
