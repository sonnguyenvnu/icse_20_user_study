/** 
 * Returns a human-friendly name of the given  {@link Symbol.TypeSymbol} for use in fixes.<ul> <li>If the type is already imported, its simple name is used. <li>If an enclosing type is imported, that enclosing type is used as a qualified. <li>Otherwise the outermost enclosing type is imported and used as a qualifier. </ul>
 */
public static String qualifyType(VisitorState state,SuggestedFix.Builder fix,Symbol sym){
  if (sym.getKind() == ElementKind.TYPE_PARAMETER) {
    return sym.getSimpleName().toString();
  }
  Deque<String> names=new ArrayDeque<>();
  for (Symbol curr=sym; curr != null; curr=curr.owner) {
    names.addFirst(curr.getSimpleName().toString());
    Symbol found=FindIdentifiers.findIdent(curr.getSimpleName().toString(),state,KindSelector.VAL_TYP);
    if (found == curr) {
      break;
    }
    if (curr.owner != null && curr.owner.getKind() == ElementKind.PACKAGE) {
      if (found != null) {
        names.addFirst(curr.owner.getQualifiedName().toString());
      }
 else {
        fix.addImport(curr.getQualifiedName().toString());
      }
      break;
    }
  }
  return Joiner.on('.').join(names);
}
