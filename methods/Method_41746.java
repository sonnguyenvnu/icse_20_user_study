public static boolean useTransaction(final JobDataMap dataMap){
  return dataMap.getBoolean(JMS_USE_TXN);
}
