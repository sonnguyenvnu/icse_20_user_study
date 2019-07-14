public static <A extends Annotation,L>LockProcessor<A,L> build(A annotation,MethodInterceptorHolder holder){
  LockProcessor<A,L> alLockProcessor=new LockProcessor<>();
  alLockProcessor.lockAnn=annotation;
  alLockProcessor.interceptorHolder=holder;
  return alLockProcessor;
}
