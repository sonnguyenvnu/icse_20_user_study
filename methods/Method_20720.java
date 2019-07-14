/** 
 * Prevents an observable from erroring on any  {@link ApiException} exceptions.
 */
public static <T>NeverApiErrorTransformer<T> neverApiError(){
  return new NeverApiErrorTransformer<>();
}
