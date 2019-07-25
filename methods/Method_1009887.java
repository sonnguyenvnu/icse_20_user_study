private static void incorporate(IdentityTokenized idt){
  tokensToTokenized.put(idt.getIdentityToken(),idt);
  if (idt instanceof PooledDataSource) {
    unclosedPooledDataSources.add(idt);
    mc.attemptManagePooledDataSource((PooledDataSource)idt);
  }
}
