/** 
 * Prevents an observable from erroring by chaining `onErrorResumeNext`.
 */
public static <T>NeverErrorTransformer<T> neverError(){
  return new NeverErrorTransformer<>();
}
