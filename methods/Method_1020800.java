@Cacheable(key="'cache_user_name_' + #name",value="userNameCache",cacheManager="cacheManager") public UserVO get(String name){
  logger.info("get by name from db");
  UserVO user=new UserVO();
  user.setId(new Random().nextLong());
  user.setName(name);
  user.setCreateTime(TimestampUtil.current());
  return user;
}
