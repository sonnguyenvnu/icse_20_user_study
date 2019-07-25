/** 
 * Retry this Solo if the predicate returns true for the latest failure Throwable.
 * @param predicate the predicate receiving the latest Throwable andif returns true, the Solo is retried.
 * @return the new Solo instance
 */
public final Solo<T> retry(Predicate<? super Throwable> predicate){
  ObjectHelper.requireNonNull(predicate,"predicate is null");
  return onAssembly(new SoloRetryWhile<T>(this,predicate));
}
