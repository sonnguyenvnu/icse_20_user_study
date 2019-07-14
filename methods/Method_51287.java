public static void registerSelfInSimpleContext(){
  ((SimpleFunctionContext)XPathFunctionContext.getInstance()).registerFunction(null,"matches",new MatchesFunction());
}
