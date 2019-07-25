/** 
 * Makes an instance for the given value. This may (but does not necessarily) return an already-allocated instance.
 * @param descriptor the method descriptor
 * @return {@code non-null;} the appropriate instance
 */
public static CstProtoRef make(CstString descriptor){
  Prototype prototype=Prototype.fromDescriptor(descriptor.getString());
  return new CstProtoRef(prototype);
}
