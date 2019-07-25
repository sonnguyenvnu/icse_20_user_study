public static List<Value> formats(){
  return ImmutableList.of(new StringValue("int32"),new StringValue("int64"),new StringValue("float"),new StringValue("double"),new StringValue("byte"),new StringValue("binary"),new StringValue("date"),new StringValue("date-time"),new StringValue("password"),new StringValue("email"),new StringValue("uuid"));
}
