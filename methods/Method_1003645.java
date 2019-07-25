/** 
 * A handler decorator implementation that does not decorate the handler. That is, it just returns the given handler.
 * @return a handler decorator that does not decorate the handler
 */
static HandlerDecorator noop(){
  return (registry,rest) -> rest;
}
