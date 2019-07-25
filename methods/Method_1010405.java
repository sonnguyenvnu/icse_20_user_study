@Override public void populate(IPropertiesPool ppool){
  if (myGenOptions != null) {
    Tuples._2<Boolean,GenerationOptions.OptionsBuilder> params=(Tuples._2<Boolean,GenerationOptions.OptionsBuilder>)ppool.properties(new ITarget.Name("jetbrains.mps.lang.core.Generate.configure"),Object.class);
    if (params != null) {
      params._1(myGenOptions);
    }
  }
}
