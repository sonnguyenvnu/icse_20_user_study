/** 
 * Applies the specified function to the specified field of the data set.
 */
@Nonnull @SuppressWarnings("unchecked") static DataFrame map(@Nonnull final DataFrame dataSet,@Nonnull final String fieldName,@Nonnull final Function1 function,@Nonnull final DataType returnType){
  final Seq<Column> inputs=Seq$.MODULE$.<Column>newBuilder().$plus$eq(dataSet.col(fieldName)).result();
  final UserDefinedFunction udf=new UserDefinedFunction(function,returnType,(Seq<DataType>)Seq$.MODULE$.<DataType>empty());
  return dataSet.withColumn(fieldName,udf.apply(inputs));
}
