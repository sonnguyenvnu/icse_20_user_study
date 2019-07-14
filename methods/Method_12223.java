@RequestMapping(value="/testUser",produces="text/html") @ResponseBody public void testMybatis(){
  User user=userMapper.getUser(1);
  System.out.println(user.getId());
}
