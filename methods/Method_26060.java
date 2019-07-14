private static boolean isVerboseLogMessage(Tree tree){
  for (; tree instanceof MethodInvocationTree; tree=getReceiver((MethodInvocationTree)tree)) {
    if (VERBOSE_LOGGING.contains(getSymbol(tree).getSimpleName().toString())) {
      return true;
    }
  }
  return false;
}
