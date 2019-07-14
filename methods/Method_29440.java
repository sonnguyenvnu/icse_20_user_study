public static String getFiveMinuteCronByHostId(long hostId){
  String baseCron=(hostId % 50) + " 0/5 * ? * *";
  return baseCron;
}
