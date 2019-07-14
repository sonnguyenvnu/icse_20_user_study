public static String makeBroadcastUrl(String userIdOrUid,long broadcastId){
  return "https://www.douban.com/people/" + userIdOrUid + "/status/" + broadcastId + "/";
}
