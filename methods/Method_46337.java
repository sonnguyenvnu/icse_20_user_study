public SofaResteasyClientBuilder logProviders(){
  if (LOGGER.isDebugEnabled()) {
    Set pcs=getProviderFactory().getProviderClasses();
    StringBuilder sb=new StringBuilder();
    sb.append("\ndefault-providers:\n");
    for (    Object provider : pcs) {
      sb.append("  ").append(provider).append("\n");
    }
    LOGGER.debug(sb.toString());
  }
  return this;
}
