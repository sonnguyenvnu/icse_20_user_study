public static String getMachineStatsCron(long hostId){
  String baseCron=(hostId % 50) + " 0/" + ConstUtils.MACHINE_STATS_CRON_MINUTE + " * ? * *";
  return baseCron;
}
