/** 
 * {@inheritDoc}
 */
@Override protected WorkData process(final ClassReader cr,final TargetClassInfoReader targetClassInfoReader){
  InvokeClassBuilder icb=new InvokeClassBuilder(destClassWriter,proxetta.getAspects(new InvokeAspect[0]),resolveClassNameSuffix(),requestedProxyClassName,targetClassInfoReader);
  cr.accept(icb,0);
  return icb.getWorkData();
}
