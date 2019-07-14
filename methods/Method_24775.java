/** 
 * Find the SimpleType from FD, SVD, VDS, etc
 * @param node
 * @return
 */
public static SimpleType extracTypeInfo(ASTNode node){
  if (node == null) {
    return null;
  }
  Type t=extracTypeInfo2(node);
  if (t instanceof PrimitiveType) {
    return null;
  }
 else   if (t instanceof ArrayType) {
    ArrayType at=(ArrayType)t;
    log("ele type " + at.getElementType() + ", " + at.getElementType().getClass().getName());
    if (at.getElementType() instanceof PrimitiveType) {
      return null;
    }
 else     if (at.getElementType() instanceof SimpleType) {
      return (SimpleType)at.getElementType();
    }
 else     return null;
  }
 else   if (t instanceof ParameterizedType) {
    ParameterizedType pmt=(ParameterizedType)t;
    log(pmt.getType() + ", " + pmt.getType().getClass());
    if (pmt.getType() instanceof SimpleType) {
      return (SimpleType)pmt.getType();
    }
 else     return null;
  }
  return (SimpleType)t;
}
