protected static List<DaoStatement> wrap(String... sqls){
  List<DaoStatement> sts=new ArrayList<DaoStatement>(sqls.length);
  for (  String sql : sqls)   if (!Strings.isBlank(sql))   sts.add(Sqls.create(sql));
  return sts;
}
