protected static Configuration restrictTo(final Configuration config,final String... fixedUmbrella){
  Preconditions.checkArgument(fixedUmbrella != null && fixedUmbrella.length > 0);
  return new Configuration(){
    private String[] concat(    String... others){
      if (others == null || others.length == 0)       return fixedUmbrella;
      String[] join=new String[fixedUmbrella.length + others.length];
      System.arraycopy(fixedUmbrella,0,join,0,fixedUmbrella.length);
      System.arraycopy(others,0,join,fixedUmbrella.length,others.length);
      return join;
    }
    @Override public boolean has(    ConfigOption option,    String... umbrellaElements){
      if (option.getNamespace().hasUmbrella())       return config.has(option,concat(umbrellaElements));
 else       return config.has(option);
    }
    @Override public <O>O get(    ConfigOption<O> option,    String... umbrellaElements){
      if (option.getNamespace().hasUmbrella())       return config.get(option,concat(umbrellaElements));
 else       return config.get(option);
    }
    @Override public Set<String> getContainedNamespaces(    ConfigNamespace umbrella,    String... umbrellaElements){
      return config.getContainedNamespaces(umbrella,concat(umbrellaElements));
    }
    @Override public Map<String,Object> getSubset(    ConfigNamespace umbrella,    String... umbrellaElements){
      return config.getSubset(umbrella,concat(umbrellaElements));
    }
    @Override public Configuration restrictTo(    String... umbrellaElements){
      return config.restrictTo(concat(umbrellaElements));
    }
  }
;
}
