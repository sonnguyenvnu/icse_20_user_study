/** 
 * ????????  in
 */
@RequestMapping(value="/testTandUIn",produces="text/html") @ResponseBody public void testTandUIn(){
  List<Integer> list=new ArrayList<Integer>();
  list.add(1);
  list.add(2);
  List<TeacherVo> teacherAndUser=userMapper.getTeacherAndUserList(list);
  System.out.println(teacherAndUser.size());
}
