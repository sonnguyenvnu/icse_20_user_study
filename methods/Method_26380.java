/** 
 * Gets the set of in-scope threadsafe type parameters from the containerOf specs on annotations. <p>Usually only the immediately enclosing declaration is searched, but it's possible to have cases like: <pre> {@literal @}MarkerAnnotation(containerOf="T") class C&lt;T&gt; { class Inner extends ThreadSafeCollection&lt;T&gt; {} } </pre>
 */
public Set<String> threadSafeTypeParametersInScope(Symbol sym){
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
AnnotationInfo annotation=getMarkerOrAcceptedAnnotation(s,state);
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
