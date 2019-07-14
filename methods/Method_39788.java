/** 
 * {@inheritDoc}
 */
@Override protected WorkData process(final ClassReader cr,final TargetClassInfoReader targetClassInfoReader){
  final ProxettaWrapperClassBuilder pcb=new ProxettaWrapperClassBuilder(targetClassOrInterface,targetInterface,targetFieldName,destClassWriter,proxetta.getAspects(new ProxyAspect[0]),resolveClassNameSuffix(),requestedProxyClassName,targetClassInfoReader,createTargetInDefaultCtor);
  cr.accept(pcb,0);
  return pcb.getWorkData();
}
