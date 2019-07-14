/** 
 * Returns a human-friendly name of the given type for use in fixes. 
 */
public static String qualifyType(VisitorState state,SuggestedFix.Builder fix,TypeMirror type){
  return type.accept(new SimpleTypeVisitor8<String,SuggestedFix.Builder>(){
    @Override protected String defaultAction(    TypeMirror e,    Builder builder){
      return e.toString();
    }
    @Override public String visitArray(    ArrayType t,    Builder builder){
      return t.getComponentType().accept(this,builder) + "[]";
    }
    @Override public String visitDeclared(    DeclaredType t,    Builder builder){
      String baseType=qualifyType(state,builder,((Type)t).tsym);
      if (t.getTypeArguments().isEmpty()) {
        return baseType;
      }
      StringBuilder b=new StringBuilder(baseType);
      b.append('<');
      boolean started=false;
      for (      TypeMirror arg : t.getTypeArguments()) {
        if (started) {
          b.append(',');
        }
        b.append(arg.accept(this,builder));
        started=true;
      }
      b.append('>');
      return b.toString();
    }
  }
,fix);
}
