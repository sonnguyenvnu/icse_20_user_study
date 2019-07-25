/** 
 * Adds the specified  {@code decorator}.
 * @param requestType the type of the {@link Request} that the {@code decorator} is interested in
 * @param responseType the type of the {@link Response} that the {@code decorator} is interested in
 * @param decorator the {@link Function} that transforms a {@link Client} to another
 * @param < T > the type of the {@link Client} being decorated
 * @param < R > the type of the {@link Client} produced by the {@code decorator}
 * @param < I > the {@link Request} type of the {@link Client} being decorated
 * @param < O > the {@link Response} type of the {@link Client} being decorated
 * @deprecated Use {@link #decorator(Function)} or {@link #rpcDecorator(Function)}.
 */
@Deprecated public <T extends Client<I,O>,R extends Client<I,O>,I extends Request,O extends Response>B decorator(Class<I> requestType,Class<O> responseType,Function<T,R> decorator){
  decoration.add(requestType,responseType,decorator);
  return self();
}
