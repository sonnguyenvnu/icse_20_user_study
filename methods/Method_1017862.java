@Override public DataSet sql(HiveContext context,String sql){
  return toDataSet(context.sql(sql));
}
