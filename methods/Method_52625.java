public static void registerSelfInSimpleContext(){
  ((SimpleFunctionContext)XPathFunctionContext.getInstance()).registerFunction(null,"typeIs",new TypeIsFunction());
}
