private static void init(Properties properties){
  MERCHANT_NOTIFY_QUEUE=properties.getProperty("tradeQueueName.notify");
  ORDER_NOTIFY_QUEUE=properties.getProperty("orderQueryQueueName.query");
}
