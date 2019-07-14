/** 
 * Pretty-prints a Type for use in fixes, qualifying any enclosed type names using  {@link #qualifyType}}.
 */
public static String prettyType(@Nullable VisitorState state,@Nullable SuggestedFix.Builder fix,Type type){
  return type.accept(new DefaultTypeVisitor<String,Void>(){
    @Override public String visitWildcardType(    Type.WildcardType t,    Void unused){
      StringBuilder sb=new StringBuilder();
      sb.append(t.kind);
      if (t.kind != BoundKind.UNBOUND) {
        sb.append(t.type.accept(this,null));
      }
      return sb.toString();
    }
    @Override public String visitClassType(    Type.ClassType t,    Void unused){
      StringBuilder sb=new StringBuilder();
      if (state == null || fix == null) {
        sb.append(t.tsym.getSimpleName());
      }
 else {
        sb.append(qualifyType(state,fix,t.tsym));
      }
      if (t.getTypeArguments().nonEmpty()) {
        sb.append('<');
        sb.append(t.getTypeArguments().stream().map(a -> a.accept(this,null)).collect(joining(", ")));
        sb.append(">");
      }
      return sb.toString();
    }
    @Override public String visitCapturedType(    Type.CapturedType t,    Void unused){
      return t.wildcard.accept(this,null);
    }
    @Override public String visitArrayType(    Type.ArrayType t,    Void unused){
      return t.elemtype.accept(this,null) + "[]";
    }
    @Override public String visitType(    Type t,    Void unused){
      return t.toString();
    }
  }
,null);
}
