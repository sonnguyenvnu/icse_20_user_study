/** 
 * Finds constructor description that matches given argument types.
 */
public CtorDescriptor getCtorDescriptor(final Class... args){
  ctors:   for (  CtorDescriptor ctorDescriptor : allCtors) {
    Class[] arg=ctorDescriptor.getParameters();
    if (arg.length != args.length) {
      continue;
    }
    for (int j=0; j < arg.length; j++) {
      if (arg[j] != args[j]) {
        continue ctors;
      }
    }
    return ctorDescriptor;
  }
  return null;
}
