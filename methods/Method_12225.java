@RequestMapping(value="/testInsert",produces="text/html") @ResponseBody public void testInsert(){
  User user=new User();
  user.setName("xiaoming");
  user.setAge(16);
  int result=userMapper.insert(user);
  System.out.println(result);
}
