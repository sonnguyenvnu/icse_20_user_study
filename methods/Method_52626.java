public static void registerSelfInSimpleContext(){
  ((SimpleFunctionContext)XPathFunctionContext.getInstance()).registerFunction(null,"typeof",new TypeOfFunction());
}
