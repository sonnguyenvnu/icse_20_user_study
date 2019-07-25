@NotNull public static AccessorsInfo build(boolean fluentValue,boolean chainValue,boolean doNotUseIsPrefix,String... prefixes){
  return new AccessorsInfo(fluentValue,chainValue,doNotUseIsPrefix,prefixes);
}
