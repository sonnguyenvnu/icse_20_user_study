private void loadDiamondDataIfPresent(final CompositePropertySource composite,final String dataId,final String diamondGroup,final boolean groupLevel){
  AcmPropertySource ps=acmPropertySourceBuilder.build(dataId,diamondGroup,groupLevel);
  if (ps != null) {
    composite.addFirstPropertySource(ps);
  }
}
