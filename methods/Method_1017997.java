/** 
 * Applies the specified function to the specified field of the data set.
 */
@Nonnull static Dataset<Row> map(@Nonnull final Dataset<Row> dataSet,@Nonnull final String fieldName,@Nonnull final Function1 function,@Nonnull final DataType returnType){
  final Seq<Column> inputs=Seq$.MODULE$.<Column>newBuilder().$plus$eq(dataSet.col(fieldName)).result();
  final UserDefinedFunction udf=new UserDefinedFunction(function,returnType,Option$.MODULE$.<Seq<DataType>>empty());
  return dataSet.withColumn(fieldName,udf.apply(inputs));
}
