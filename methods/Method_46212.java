/** 
 * ??????
 * @param invoker ???
 * @return ????????
 */
@Override public boolean needToLoad(FilterInvoker invoker){
  ConsumerConfig consumerConfig=(ConsumerConfig)invoker.getConfig();
  return consumerConfig.isGeneric();
}
