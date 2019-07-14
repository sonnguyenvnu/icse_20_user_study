/** 
 * Returns a  {@link StaticImports} if the given import is a static single-type import. Returns{@code null} otherwise, e.g. because the import is non-static, or an on-demand import, orstatically imports a field or method.
 */
@Nullable public static StaticImportInfo tryCreate(ImportTree tree,VisitorState state){
  if (!tree.isStatic()) {
    return null;
  }
  if (!(tree.getQualifiedIdentifier() instanceof JCTree.JCFieldAccess)) {
    return null;
  }
  JCTree.JCFieldAccess access=(JCTree.JCFieldAccess)tree.getQualifiedIdentifier();
  Name identifier=access.getIdentifier();
  if (identifier.contentEquals("*")) {
    return null;
  }
  Symbol importedType=ASTHelpers.getSymbol(access.getExpression());
  if (importedType == null) {
    return null;
  }
  Types types=state.getTypes();
  Type canonicalType=types.erasure(importedType.asType());
  if (canonicalType == null) {
    return null;
  }
  Symbol.TypeSymbol baseType;
{
    Symbol sym=ASTHelpers.getSymbol(access.getExpression());
    if (!(sym instanceof Symbol.TypeSymbol)) {
      return null;
    }
    baseType=(Symbol.TypeSymbol)sym;
  }
  Symbol.PackageSymbol pkgSym=((JCTree.JCCompilationUnit)state.getPath().getCompilationUnit()).packge;
  ImmutableSet<Symbol> members=lookup(baseType,baseType,identifier,types,pkgSym);
  if (members.isEmpty()) {
    return null;
  }
  Type canonicalOwner=null;
  for (  Symbol member : members) {
    Type owner=types.erasure(member.owner.type);
    if (canonicalOwner == null || types.isSubtype(owner,canonicalOwner)) {
      canonicalOwner=owner;
    }
  }
  if (canonicalOwner == null) {
    return null;
  }
  if (members.size() == 1 && getOnlyElement(members) instanceof ClassSymbol) {
    return StaticImportInfo.create(access.toString(),getOnlyElement(members).toString());
  }
  return StaticImportInfo.create(access.getExpression().toString(),canonicalOwner.toString(),identifier.toString(),members);
}
