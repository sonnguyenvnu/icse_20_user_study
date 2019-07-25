/** 
 * Adds the specified  {@code decorator}.
 * @param requestType the type of the {@link Request} that the {@code decorator} is interested in
 * @param responseType the type of the {@link Response} that the {@code decorator} is interested in
 * @param decorator the {@link DecoratingClientFunction} that intercepts an invocation
 * @param < I > the {@link Request} type of the {@link Client} being decorated
 * @param < O > the {@link Response} type of the {@link Client} being decorated
 * @deprecated Use {@link #decorator(DecoratingClientFunction)} or{@link #rpcDecorator(DecoratingClientFunction)}.
 */
@Deprecated public <I extends Request,O extends Response>B decorator(Class<I> requestType,Class<O> responseType,DecoratingClientFunction<I,O> decorator){
  decoration.add(requestType,responseType,decorator);
  return self();
}
