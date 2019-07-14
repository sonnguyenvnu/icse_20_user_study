private static Fix fixByCatchingException(TryTree tryTree){
  VariableTree catchParameter=getOnlyCatch(tryTree).getParameter();
  return replace(catchParameter,"Exception " + catchParameter.getName());
}
