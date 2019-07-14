/** 
 * ????????
 */
@RequestMapping(value="/testTandU",produces="text/html") @ResponseBody public void testTandU(){
  List<TeacherVo> teacherAndUser=userMapper.getTeacherAndUser(1);
  System.out.println(teacherAndUser.size());
}
