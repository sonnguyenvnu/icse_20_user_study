public boolean getNickNameCount(String userName){
  return miaoShaUserMapper.getCountByUserName(userName,USERTYPE_NORMAL) <= 0;
}
