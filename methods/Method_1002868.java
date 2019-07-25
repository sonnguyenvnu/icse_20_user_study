/** 
 * Creates a new  {@link Service} that decorates this {@link Service} with the specified{@link DecoratingServiceFunction}.
 */
default Service<I,O> decorate(DecoratingServiceFunction<I,O> function){
  return new FunctionalDecoratingService<>(this,function);
}
