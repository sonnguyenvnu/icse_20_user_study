@Override public MapPropertySource locate(Environment environment){
  return environment instanceof ConfigurableEnvironment ? new SecretsPropertySource(this.client,environment,this.properties) : null;
}
