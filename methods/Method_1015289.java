@RequestMapping("/") public Result index(){
  jdbcTemplate1.update(Sql.addUser,"xiaomo",20);
  jdbcTemplate2.update(Sql.addUser,"xiaoming",30);
  int count1=jdbcTemplate1.queryForObject(Sql.selectUser,Integer.class);
  int count2=jdbcTemplate2.queryForObject(Sql.selectUser,Integer.class);
  return new Result<>(new Object[]{count1,count2});
}
