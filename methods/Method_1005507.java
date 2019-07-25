@Override public PropertySource locate(Environment environment){
  if (environment instanceof ConfigurableEnvironment) {
    ConfigurableEnvironment env=(ConfigurableEnvironment)environment;
    List<ConfigMapConfigProperties.NormalizedSource> sources=this.properties.determineSources();
    CompositePropertySource composite=new CompositePropertySource("composite-configmap");
    if (this.properties.isEnableApi()) {
      sources.forEach(s -> composite.addFirstPropertySource(getMapPropertySourceForSingleConfigMap(env,s)));
    }
    addPropertySourcesFromPaths(environment,composite);
    return composite;
  }
  return null;
}
