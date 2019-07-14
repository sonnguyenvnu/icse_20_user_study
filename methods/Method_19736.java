@HystrixCommand public List<User> findUserBatch(List<Long> ids){
  log.info("????????,ids: " + ids);
  User[] users=restTemplate.getForObject("http://Server-Provider/user/users?ids={1}",User[].class,StringUtils.join(ids,","));
  return Arrays.asList(users);
}
