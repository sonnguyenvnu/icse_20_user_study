private void execute(String sql,boolean ddl){
  Prepared command=session.prepare(sql);
  command.update();
  if (ddl) {
    session.commit(true);
  }
}
