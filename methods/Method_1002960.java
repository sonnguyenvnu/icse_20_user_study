/** 
 * Adds a callback that transfers the outcome of the specified  {@link CompletionStage} to the specified{@link AsyncMethodCallback}. <pre> {@code}> public class MyThriftService implements ThriftService.AsyncIface  >     @Override >     public void myServiceMethod(AsyncMethodCallback<MyResult> callback) { >         final CompletableFuture<MyResult> future = ...; >         AsyncMethodCallbacks.transfer(future, callback); >     } > } }</pre>
 */
public static <T>void transfer(CompletionStage<T> src,AsyncMethodCallback<? super T> dest){
  requireNonNull(src,"src");
  requireNonNull(dest,"dest");
  src.handle((res,cause) -> {
    try {
      if (cause != null) {
        invokeOnError(dest,cause);
      }
 else {
        dest.onComplete(res);
      }
    }
 catch (    Exception e) {
      CompletionActions.log(e);
    }
    return null;
  }
);
}
