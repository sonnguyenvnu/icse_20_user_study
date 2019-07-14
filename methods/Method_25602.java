/** 
 * Build an instance of a Type. 
 */
public Type getType(Type baseType,boolean isArray,List<Type> typeParams){
  boolean isGeneric=typeParams != null && !typeParams.isEmpty();
  if (!isArray && !isGeneric) {
    return baseType;
  }
 else   if (isArray && !isGeneric) {
    ClassSymbol arraySymbol=getSymtab().arrayClass;
    return new ArrayType(baseType,arraySymbol);
  }
 else   if (!isArray && isGeneric) {
    com.sun.tools.javac.util.List<Type> typeParamsCopy=com.sun.tools.javac.util.List.from(typeParams);
    return new ClassType(Type.noType,typeParamsCopy,baseType.tsym);
  }
 else {
    throw new IllegalArgumentException("Unsupported arguments to getType");
  }
}
