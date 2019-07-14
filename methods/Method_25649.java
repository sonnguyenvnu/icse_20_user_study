/** 
 * Returns a string descriptor of a method's reference type. 
 */
private String methodReferenceDescriptor(Types types,MethodSymbol sym){
  StringBuilder sb=new StringBuilder();
  sb.append(sym.getSimpleName()).append('(');
  if (!sym.isStatic()) {
    sb.append(Signatures.descriptor(sym.owner.type,types));
  }
  sym.params().stream().map(p -> Signatures.descriptor(p.type,types)).forEachOrdered(sb::append);
  sb.append(")");
  return sb.toString();
}
