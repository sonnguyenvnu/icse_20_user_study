/** 
 * {@inheritDoc}
 */
@Override protected WorkData process(final ClassReader cr,final TargetClassInfoReader targetClassInfoReader){
  ProxettaClassBuilder pcb=new ProxettaClassBuilder(destClassWriter,proxetta.getAspects(new ProxyAspect[0]),resolveClassNameSuffix(),requestedProxyClassName,targetClassInfoReader);
  cr.accept(pcb,0);
  return pcb.getWorkData();
}
