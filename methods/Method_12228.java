/** 
 * ????????  in
 */
@RequestMapping(value="/testAssc",produces="text/html") @ResponseBody public void testAssc(){
  List<TeacherListVo> teacherAndUser=userMapper.getTeacherAndUserListVo(1);
  System.out.println(teacherAndUser.toString());
}
