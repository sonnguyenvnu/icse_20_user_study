@RequestMapping("querystudentsfromoracle") public List<Map<String,Object>> queryStudentsFromOracle(){
  return this.studentService.getAllStudentsFromOralce();
}
