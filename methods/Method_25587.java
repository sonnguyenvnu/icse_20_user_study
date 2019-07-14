/** 
 * Pretty-prints a method signature for use in diagnostics. <p>Uses simple names for declared types, and omitting formal type parameters and the return type since they do not affect overload resolution.
 */
public static String prettyMethodSignature(ClassSymbol origin,MethodSymbol m){
  StringBuilder sb=new StringBuilder();
  if (m.isConstructor()) {
    Name name=m.owner.enclClass().getSimpleName();
    if (name.isEmpty()) {
      name=m.owner.enclClass().getSuperclass().asElement().getSimpleName();
    }
    sb.append(name);
  }
 else {
    if (!m.owner.equals(origin)) {
      sb.append(m.owner.getSimpleName()).append('.');
    }
    sb.append(m.getSimpleName());
  }
  sb.append(m.getParameters().stream().map(v -> v.type.accept(PRETTY_TYPE_VISITOR,null)).collect(joining(", ","(",")")));
  return sb.toString();
}
