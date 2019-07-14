/** 
 * Check a single class' fields for immutability.
 * @param immutableTyParams the in-scope immutable type parameters
 * @param classType the type to check the fields of
 */
Violation areFieldsImmutable(Optional<ClassTree> tree,ImmutableSet<String> immutableTyParams,ClassType classType,ViolationReporter reporter){
  ClassSymbol classSym=(ClassSymbol)classType.tsym;
  if (classSym.members() == null) {
    return Violation.absent();
  }
  Filter<Symbol> instanceFieldFilter=new Filter<Symbol>(){
    @Override public boolean accepts(    Symbol symbol){
      return symbol.getKind() == ElementKind.FIELD && !symbol.isStatic();
    }
  }
;
  Map<Symbol,Tree> declarations=new HashMap<>();
  if (tree.isPresent()) {
    for (    Tree member : tree.get().getMembers()) {
      Symbol sym=ASTHelpers.getSymbol(member);
      if (sym != null) {
        declarations.put(sym,member);
      }
    }
  }
  List<Symbol> members=ImmutableList.copyOf(classSym.members().getSymbols(instanceFieldFilter)).reverse();
  for (  Symbol member : members) {
    Optional<Tree> memberTree=Optional.ofNullable(declarations.get(member));
    Violation info=isFieldImmutable(memberTree,immutableTyParams,classSym,classType,(VarSymbol)member,reporter);
    if (info.isPresent()) {
      return info;
    }
  }
  return Violation.absent();
}
