public static synchronized IdentityTokenized reregister(IdentityTokenized idt){
  if (idt instanceof PooledDataSource) {
    banner();
    attemptRegisterRegistryMBean();
  }
  if (idt.getIdentityToken() == null)   throw new RuntimeException("[c3p0 issue] The identityToken of a registered object should be set prior to registration.");
  IdentityTokenized coalesceCheck=(IdentityTokenized)idtCoalescer.coalesce(idt);
  if (!isIncorporated(coalesceCheck))   incorporate(coalesceCheck);
  return coalesceCheck;
}
