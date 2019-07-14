public ApiRequest<List<me.zhanghai.android.douya.network.api.info.apiv2.Broadcast>> getApiV2BroadcastList(String userIdOrUid,String topic,Long untilId,Integer count){
  String url;
  if (TextUtils.isEmpty(userIdOrUid) && TextUtils.isEmpty(topic)) {
    url="lifestream/home_timeline";
  }
 else   if (TextUtils.isEmpty(topic)) {
    url=StringUtils.formatUs("lifestream/user_timeline/%s",userIdOrUid);
  }
 else {
    url="lifestream/topics";
  }
  return mLifeStreamService.getBroadcastList(url,untilId,count,topic);
}
