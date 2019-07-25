protected static List<DaoStatement> wrap(List<String> sqls){
  List<DaoStatement> sts=new ArrayList<DaoStatement>(sqls.size());
  for (  String sql : sqls)   if (!Strings.isBlank(sql))   sts.add(Sqls.create(sql));
  return sts;
}
