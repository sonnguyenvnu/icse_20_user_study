@Override public void populate(IPropertiesPool ppool){
  Tuples._1<Boolean> tparams=(Tuples._1<Boolean>)ppool.properties(new ITarget.Name("jetbrains.mps.lang.core.TextGen.textGen"),Object.class);
  if (tparams != null) {
    tparams._0(myDebugInfo);
  }
}
