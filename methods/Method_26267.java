/** 
 * Try to qualify the type, or return the full name. 
 */
public static String qualifyType(VisitorState state,SuggestedFix.Builder fix,Symbol sym){
  Deque<String> names=new ArrayDeque<>();
  for (Symbol curr=sym; curr != null; curr=curr.owner) {
    names.addFirst(curr.getSimpleName().toString());
    Symbol found=findIdent(curr.getSimpleName().toString(),state,KindSelector.VAL_TYP);
    if (found == curr) {
      break;
    }
    if (curr.getKind() == ElementKind.PACKAGE) {
      return sym.getQualifiedName().toString();
    }
    if (found == null) {
      fix.addImport(curr.getQualifiedName().toString());
      break;
    }
  }
  return Joiner.on('.').join(names);
}
