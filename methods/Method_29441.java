public static String getRedisSlowLogCron(long appId){
  Random random=new Random();
  String baseCron=random.nextInt(60) + " 0/20 * ? * *";
  return baseCron;
}
