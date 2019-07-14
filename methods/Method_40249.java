/** 
 * Assign some definite value to the attribute.  Used during the name resolution pass.  This method is called when this node is in the lvalue of an assignment, in which case it is called in lieu of  {@link #resolve}.<p>
 */
public void setAttr(Scope s,Type v,int tag) throws Exception {
  Type targetType=resolveExpr(target,s,tag);
  if (targetType.isUnionType()) {
    Set<Type> types=targetType.asUnionType().getTypes();
    for (    Type tp : types) {
      setAttrType(tp,v,tag);
    }
  }
 else {
    setAttrType(targetType,v,tag);
  }
}
