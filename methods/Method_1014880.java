@PreAuthorize("#id<10 and principal.username.equals(#username) and #user.username.equals('abc')") @PostAuthorize("returnObject%2==0") @RequestMapping("/grep") public Integer test(Integer id,String username,UserEntity user){
  return id;
}
