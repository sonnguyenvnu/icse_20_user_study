/** 
 * ??????
 * @return List<User>
 */
private static List<User> users(){
  List<User> users=new ArrayList<>();
  users.add(new User("admin","bfc62b3f67a4c3e57df84dad8cc48a3b",new HashSet<>(Collections.singletonList("admin")),new HashSet<>(Arrays.asList("user:add","user:view"))));
  users.add(new User("scott","11bd73355c7bbbac151e4e4f943e59be",new HashSet<>(Collections.singletonList("regist")),new HashSet<>(Collections.singletonList("user:view"))));
  return users;
}
