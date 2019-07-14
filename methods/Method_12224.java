@RequestMapping(value="/testSelectLIst",produces="text/html") @ResponseBody public void testSelectUser(){
  List<User> result=userMapper.getUserList(null,"");
  System.out.println(result.size());
}
