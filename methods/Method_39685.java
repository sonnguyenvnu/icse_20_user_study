/** 
 * Returns <code>true</code> if type name equals param type.
 */
boolean isEqualTypeName(final Type argumentType,final Class paramType){
  String s=argumentType.getClassName();
  if (s.endsWith(ARRAY)) {
    final String prefix=s.substring(0,s.length() - 2);
    final String bytecodeSymbol=primitives.get(prefix);
    if (bytecodeSymbol != null) {
      s='[' + bytecodeSymbol;
    }
 else {
      s="[L" + prefix + ';';
    }
  }
  return s.equals(paramType.getName());
}
