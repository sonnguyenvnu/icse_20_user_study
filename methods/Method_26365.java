/** 
 * Gets the set of in-scope immutable type parameters from the containerOf specs on {@code @Immutable} annotations.<p>Usually only the immediately enclosing declaration is searched, but it's possible to have cases like: <pre>
 * @Immutable(containerOf="T") class C<T> {class Inner extends ImmutableCollection<T> {} } </pre>
 */
private static ImmutableSet<String> immutableTypeParametersInScope(Symbol sym,VisitorState state,ImmutableAnalysis analysis){
  if (sym == null) {
    return ImmutableSet.of();
  }
  ImmutableSet.Builder<String> result=ImmutableSet.builder();
  OUTER:   for (Symbol s=sym; s.owner != null; s=s.owner) {
switch (s.getKind()) {
case INSTANCE_INIT:
      continue;
case PACKAGE:
    break OUTER;
default :
  break;
}
AnnotationInfo annotation=analysis.getImmutableAnnotation(s,state);
if (annotation == null) {
continue;
}
for (TypeVariableSymbol typaram : s.getTypeParameters()) {
String name=typaram.getSimpleName().toString();
if (annotation.containerOf().contains(name)) {
  result.add(name);
}
}
if (s.isStatic()) {
break;
}
}
return result.build();
}
