/** 
 * Allows to shorten the callback creation by passing a lambda as first parameter, and another callback used just in case of error propagation
 * @param successCallback the lambda containing the operation to do onSuccess
 * @param errorCallback the callback called in case of exception propagation
 * @return a regular {@link Callback}
 */
public static <T>Callback<T> handle(final SuccessCallback<T> successCallback,Callback<?> errorCallback){
  return new Callback<T>(){
    @Override public void onError(    Throwable e){
      errorCallback.onError(e);
    }
    @Override public void onSuccess(    T result){
      successCallback.onSuccess(result);
    }
  }
;
}
