@RequestMapping("querystudentsfrommysql") public List<Map<String,Object>> queryStudentsFromMysql(){
  return this.studentService.getAllStudentsFromMysql();
}
