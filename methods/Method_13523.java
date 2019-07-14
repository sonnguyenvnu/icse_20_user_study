@Bean public ApplicationRunner userServiceRunner(){
  return arguments -> {
    User user=new User();
    user.setId(1L);
    user.setName("???");
    user.setAge(33);
    System.out.printf("UserService.save(%s) : %s\n",user,userService.save(user));
    System.out.printf("UserService.findAll() : %s\n",user,userService.findAll());
    System.out.printf("UserService.remove(%d) : %s\n",user.getId(),userService.remove(user.getId()));
  }
;
}
