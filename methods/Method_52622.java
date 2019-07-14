public static boolean typeIs(final XPathContext context,final String fullTypeName){
  return TypeIsFunction.typeIs((Node)((ElementNode)context.getContextItem()).getUnderlyingNode(),fullTypeName);
}
