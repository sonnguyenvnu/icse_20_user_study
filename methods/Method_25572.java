/** 
 * Finds the set of all bare variable identifiers in scope at the current location. Identifiers are ordered by ascending distance/scope count from the current location to match shadowing rules. That is, if two variables with the same simple names appear in the set, the one that appears first in iteration order is the one you get if you use the bare name in the source code. <p>We do not report variables that would require a qualified access. We also do not handle wildcard imports.
 */
public static LinkedHashSet<VarSymbol> findAllIdents(VisitorState state){
  ImmutableSet.Builder<VarSymbol> result=new ImmutableSet.Builder<>();
  Tree prev=state.getPath().getLeaf();
  for (  Tree curr : state.getPath().getParentPath()) {
switch (curr.getKind()) {
case BLOCK:
      for (      StatementTree stmt : ((BlockTree)curr).getStatements()) {
        if (stmt.equals(prev)) {
          break;
        }
        addIfVariable(stmt,result);
      }
    break;
case METHOD:
  for (  VariableTree param : ((MethodTree)curr).getParameters()) {
    result.add(ASTHelpers.getSymbol(param));
  }
break;
case CATCH:
result.add(ASTHelpers.getSymbol(((CatchTree)curr).getParameter()));
break;
case CLASS:
case INTERFACE:
case ENUM:
case ANNOTATION_TYPE:
for (Tree member : ((ClassTree)curr).getMembers()) {
if (member.equals(prev)) {
break;
}
addIfVariable(member,result);
}
Type classType=ASTHelpers.getType(curr);
List<Type> classTypeClosure=state.getTypes().closure(classType);
List<Type> superTypes=classTypeClosure.size() <= 1 ? Collections.emptyList() : classTypeClosure.subList(1,classTypeClosure.size());
for (Type type : superTypes) {
Scope scope=type.tsym.members();
ImmutableList.Builder<VarSymbol> varsList=ImmutableList.builder();
for (Symbol var : scope.getSymbols(VarSymbol.class::isInstance)) {
varsList.add((VarSymbol)var);
}
result.addAll(varsList.build().reverse());
}
break;
case FOR_LOOP:
addAllIfVariable(((ForLoopTree)curr).getInitializer(),result);
break;
case ENHANCED_FOR_LOOP:
result.add(ASTHelpers.getSymbol(((EnhancedForLoopTree)curr).getVariable()));
break;
case TRY:
TryTree tryTree=(TryTree)curr;
boolean inResources=false;
for (Tree resource : tryTree.getResources()) {
if (resource.equals(prev)) {
inResources=true;
break;
}
}
if (inResources) {
for (Tree resource : tryTree.getResources()) {
if (resource.equals(prev)) {
break;
}
addIfVariable(resource,result);
}
}
 else if (tryTree.getBlock().equals(prev)) {
addAllIfVariable(tryTree.getResources(),result);
}
break;
case COMPILATION_UNIT:
for (ImportTree importTree : ((CompilationUnitTree)curr).getImports()) {
if (importTree.isStatic() && importTree.getQualifiedIdentifier().getKind() == Kind.MEMBER_SELECT) {
MemberSelectTree memberSelectTree=(MemberSelectTree)importTree.getQualifiedIdentifier();
Scope scope=state.getTypes().membersClosure(ASTHelpers.getType(memberSelectTree.getExpression()),false);
for (Symbol var : scope.getSymbols(sym -> sym instanceof VarSymbol && sym.getSimpleName().equals(memberSelectTree.getIdentifier()))) {
result.add((VarSymbol)var);
}
}
}
break;
default :
break;
}
prev=curr;
}
return result.build().stream().filter(var -> isVisible(var,state.getPath())).collect(Collectors.toCollection(LinkedHashSet::new));
}
