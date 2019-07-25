@Override public <O>Promise<O> transform(Function<? super Upstream<? extends T>,? extends Upstream<O>> upstreamTransformer){
  try {
    return new DefaultPromise<>(upstreamTransformer.apply(upstream));
  }
 catch (  Exception e) {
    throw Exceptions.uncheck(e);
  }
}
