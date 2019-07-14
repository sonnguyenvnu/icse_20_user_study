public Table typedParse(InputStream input,String options) throws IOException {
  Table table=new Table();
  table.setColumnTypes(this);
  table.parse(input,options);
  return table;
}
