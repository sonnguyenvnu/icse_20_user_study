@Override public List<Student> query(){
  final List<Student> students=new ArrayList<Student>();
  String sql="select id, name , age from student";
  jdbcTemplate.query(sql,new RowCallbackHandler(){
    @Override public void processRow(    ResultSet rs) throws SQLException {
      int id=rs.getInt("id");
      String name=rs.getString("name");
      int age=rs.getInt("age");
      Student student=new Student();
      student.setId(id);
      student.setName(name);
      student.setAge(age);
      students.add(student);
    }
  }
);
  return students;
}
