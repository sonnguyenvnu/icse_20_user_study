@Override public Student queryStudentBySno(String sno){
  String sql="select * from student where sno = ?";
  Object[] args={sno};
  int[] argTypes={Types.VARCHAR};
  List<Student> studentList=this.jdbcTemplate.query(sql,args,argTypes,new StudentMapper());
  if (studentList != null && studentList.size() > 0) {
    return studentList.get(0);
  }
 else {
    return null;
  }
}
