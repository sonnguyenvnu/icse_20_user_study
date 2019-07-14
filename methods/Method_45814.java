/** 
 * ??????
 * @param consumerBootstrap ????
 * @return ????????
 */
@Override public boolean needToLoad(ConsumerBootstrap consumerBootstrap){
  return StringUtils.isNotEmpty(consumerBootstrap.getConsumerConfig().getDirectUrl());
}
