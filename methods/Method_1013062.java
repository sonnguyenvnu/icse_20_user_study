public static List<Field> path(){
  return ImmutableList.of(new RefField(),new OperationField("get"),new OperationField("put"),new OperationField("post"),new OperationField("delete"),new OperationField("options"),new OperationField("head"),new OperationField("patch"),new ArrayField("parameters"));
}
