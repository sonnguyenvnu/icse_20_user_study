@Override public void collect(@NotNull TwigFileVariableCollectorParameter parameter,@NotNull Map<String,Set<String>> variables){
  if (!(parameter.getElement().getContainingFile() instanceof TwigFile)) {
    return;
  }
  variables.putAll(convertHashMapToTypeSet(TwigTypeResolveUtil.findFileVariableDocBlock((TwigFile)parameter.getElement().getContainingFile())));
}
