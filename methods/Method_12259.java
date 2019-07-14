public boolean checkPath(MiaoshaUser user,long goodsId,String path){
  if (user == null || path == null) {
    return false;
  }
  String pathOld=redisService.get(MiaoshaKey.getMiaoshaPath,"" + user.getNickname() + "_" + goodsId,String.class);
  return path.equals(pathOld);
}
