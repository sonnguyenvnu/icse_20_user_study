@Deprecated public static boolean typeof(final XPathContext context,final String nodeTypeName,final String fullTypeName,final String shortTypeName){
  return TypeOfFunction.typeof((Node)((ElementNode)context.getContextItem()).getUnderlyingNode(),nodeTypeName,fullTypeName,shortTypeName);
}
