public static String makeBroadcastListUrl(String uidOrUserId,String topic){
  if (!TextUtils.isEmpty(uidOrUserId)) {
    return "https://www.douban.com/people/" + uidOrUserId + "/statuses/";
  }
 else {
    return "https://www.douban.com/update/topic/" + Uri.encode(topic) + "/";
  }
}
