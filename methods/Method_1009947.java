/** 
 * ??????????
 * @param tClass
 * @param serviceImpl
 * @param < T >
 * @return
 */
private static <T>T proxy(@NonNull final Class<T> tClass,final T serviceImpl){
  return (T)Proxy.newProxyInstance(tClass.getClassLoader(),new Class[]{tClass},new InvocationHandler(){
    @Override public Object invoke(    Object proxy,    Method method,    Object[] args) throws Throwable {
      try {
        Class<?> returnType=method.getReturnType();
        int rxType=-1;
        if (returnType == Single.class) {
          rxType=1;
        }
 else         if (returnType == Observable.class) {
          rxType=2;
        }
 else         if (returnType == Flowable.class) {
          rxType=3;
        }
 else         if (returnType == Maybe.class) {
          rxType=4;
        }
 else         if (returnType == Completable.class) {
          rxType=5;
        }
        Object result=method.invoke(serviceImpl,args);
        if (rxType == 1) {
          result=((Single)result).onErrorResumeNext(new Function<Throwable,SingleSource>(){
            @Override public SingleSource apply(            Throwable throwable) throws Exception {
              return Single.error(new RxJavaException(throwable));
            }
          }
);
        }
 else         if (rxType == 2) {
          result=((Observable)result).onErrorResumeNext(new Function<Throwable,ObservableSource>(){
            @Override public ObservableSource apply(            Throwable throwable) throws Exception {
              return Observable.error(new RxJavaException(throwable));
            }
          }
);
        }
 else         if (rxType == 3) {
          result=((Flowable)result).onErrorResumeNext(new Function<Throwable,Publisher>(){
            @Override public Publisher apply(            Throwable throwable) throws Exception {
              return Flowable.error(new RxJavaException(throwable));
            }
          }
);
        }
 else         if (rxType == 4) {
          result=((Maybe)result).onErrorResumeNext(new Function<Throwable,MaybeSource>(){
            @Override public MaybeSource apply(            Throwable throwable) throws Exception {
              return Maybe.error(new RxJavaException(throwable));
            }
          }
);
        }
 else         if (rxType == 5) {
          result=((Completable)result).onErrorResumeNext(new Function<Throwable,CompletableSource>(){
            @Override public CompletableSource apply(            Throwable throwable) throws Exception {
              return Completable.error(new RxJavaException(throwable));
            }
          }
);
        }
        return result;
      }
 catch (      Exception e) {
        throw new ServiceInvokeException(e);
      }
    }
  }
);
}
