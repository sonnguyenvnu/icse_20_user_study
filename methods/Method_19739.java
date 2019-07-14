@CacheRemove(commandKey="getUserById") @HystrixCommand public void updateUser(@CacheKey("id") User user){
  this.restTemplate.put("http://Server-Provider/user",user);
}
