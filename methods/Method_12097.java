public String createMiaoshaPath(MiaoshaUser user,long goodsId){
  if (user == null || goodsId <= 0) {
    return null;
  }
  String str=MD5Utils.md5(UUIDUtil.uuid() + "123456");
  redisService.set(MiaoshaKey.getMiaoshaPath,"" + user.getNickname() + "_" + goodsId,str);
  return str;
}
