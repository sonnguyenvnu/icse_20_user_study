public static void begin(){
  DefaultTransactionDefinition def=new DefaultTransactionDefinition();
  def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
  PlatformTransactionManager tm=SpringHelper.getObject(DataSourceTransactionManager.class);
  TransactionStatus status=tm.getTransaction(def);
  String key=getKey();
  map.put(key,status);
}
