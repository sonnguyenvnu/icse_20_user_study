static public int[] countColumnsRows(final Project project,final Engine engine,Properties params){
  RowCountingTabularSerializer serializer=new RowCountingTabularSerializer();
  exportRows(project,engine,params,serializer);
  return new int[]{serializer.columns,serializer.rows};
}
