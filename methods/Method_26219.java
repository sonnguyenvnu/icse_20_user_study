/** 
 * Find the root variable identifiers from an arbitrary expression. <p>Examples: a.trim().intern() ==> {a} a.b.trim().intern() ==> {a} this.intValue.foo() ==> {this} this.foo() ==> {this} intern() ==> {} String.format() ==> {} java.lang.String.format() ==> {} x.y.z(s.t) ==> {x,s}
 */
static List<IdentifierTree> getVariableUses(ExpressionTree tree){
  final List<IdentifierTree> freeVars=new ArrayList<>();
  new TreeScanner<Void,Void>(){
    @Override public Void visitIdentifier(    IdentifierTree node,    Void v){
      if (((JCIdent)node).sym instanceof VarSymbol) {
        freeVars.add(node);
      }
      return super.visitIdentifier(node,v);
    }
  }
.scan(tree,null);
  return freeVars;
}
