/** 
 * hystrix fallback??
 * @param id id
 * @return ?????
 */
public User fallback(Long id){
  RibbonHystrixService.LOGGER.info("???????fallback?????????id = {}",id);
  User user=new User();
  user.setId(-1L);
  user.setUsername("default username");
  user.setAge(0);
  return user;
}
