/** 
 * Returns the default ctor or <code>null</code> if not found.
 */
public CtorDescriptor getDefaultCtorDescriptor(final boolean declared){
  CtorDescriptor defaultCtor=getCtors().getDefaultCtor();
  if ((defaultCtor != null) && defaultCtor.matchDeclared(declared)) {
    return defaultCtor;
  }
  return null;
}
