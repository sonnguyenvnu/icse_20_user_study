/** 
 * Creates a  {@link Setter}.
 */
protected Setter createSetter(final boolean declared){
  if (writeMethodDescriptor != null) {
    if (writeMethodDescriptor.matchDeclared(declared)) {
      return Setter.of(writeMethodDescriptor);
    }
  }
  if (fieldDescriptor != null) {
    if (fieldDescriptor.matchDeclared(declared)) {
      return Setter.of(fieldDescriptor);
    }
  }
  return null;
}
