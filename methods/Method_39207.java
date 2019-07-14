/** 
 * Initializes and starts web application.
 */
public WebApp start(final Consumer<MadvocRouter> madvocRouterConsumer){
  madvocRouterConsumers.add(madvocRouterConsumer);
  return start();
}
