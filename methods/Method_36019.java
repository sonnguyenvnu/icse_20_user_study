public RequestPatternBuilder andMatching(String customRequestMatcherName,Parameters parameters){
  this.customMatcherDefinition=new CustomMatcherDefinition(customRequestMatcherName,parameters);
  return this;
}
