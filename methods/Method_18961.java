/** 
 * Attempt to recover saved parameter names for a method. This will likely only work for code compiled with javac >= 8, but it's often the only chance to get named parameters as opposed to 'arg0', 'arg1', ...
 */
@Nullable private static List<Name> getSavedParameterNames(ExecutableElement method){
  if (method instanceof Symbol.MethodSymbol) {
    final Symbol.MethodSymbol methodSymbol=(Symbol.MethodSymbol)method;
    try {
      return (List<Name>)Symbol.MethodSymbol.class.getField("savedParameterNames").get(methodSymbol);
    }
 catch (    NoSuchFieldError|IllegalAccessException|NoSuchFieldException ignored) {
      return null;
    }
  }
  return null;
}
