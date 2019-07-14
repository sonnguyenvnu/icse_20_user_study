/** 
 * Creates a  {@link Getter}.
 */
protected Getter createGetter(final boolean declared){
  if (readMethodDescriptor != null) {
    if (readMethodDescriptor.matchDeclared(declared)) {
      return Getter.of(readMethodDescriptor);
    }
  }
  if (fieldDescriptor != null) {
    if (fieldDescriptor.matchDeclared(declared)) {
      return Getter.of(fieldDescriptor);
    }
  }
  return null;
}
