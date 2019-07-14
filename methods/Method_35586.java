public ResponseDefinitionBuilder withUniformRandomDelay(int lowerMilliseconds,int upperMilliseconds){
  return withRandomDelay(new UniformDistribution(lowerMilliseconds,upperMilliseconds));
}
