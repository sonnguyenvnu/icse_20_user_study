@Override public Collection<SNode> weave(@NotNull NodeWeaveFacility.WeaveContext weaveContext,@NotNull NodeWeaveFacility weaveSupport) throws GenerationException {
  ArrayList<SNode> rv=new ArrayList<SNode>();
  applyPart0(weaveSupport.getTemplateContext()).weaveWith(weaveSupport).reportTo(rv);
  applyPart1(weaveSupport.getTemplateContext()).weaveWith(weaveSupport).reportTo(rv);
  return rv;
}
