public static void onBytesSent(int amount,int networkType,final int currentAccount){
  try {
    StatsController.getInstance(currentAccount).incrementSentBytesCount(networkType,StatsController.TYPE_TOTAL,amount);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
