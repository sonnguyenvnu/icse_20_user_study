@Override public Seq<Object> call(final Row row){
  final Builder<Object,Seq<Object>> key=Seq$.MODULE$.newBuilder();
  for (  final String column : groupByColumns) {
    final Object columnValue=row.getAs(column);
    if (columnValue instanceof byte[]) {
      key.$plus$eq(Arrays.toString((byte[])columnValue));
    }
 else {
      key.$plus$eq(columnValue);
    }
  }
  return key.result();
}
