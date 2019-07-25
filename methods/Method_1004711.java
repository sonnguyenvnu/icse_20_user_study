@Override public String write(InitializrMetadata metadata,String appUrl){
  ObjectNode delegate=nodeFactory.objectNode();
  links(delegate,metadata.getTypes().getContent(),appUrl);
  dependencies(delegate,metadata.getDependencies());
  type(delegate,metadata.getTypes());
  singleSelect(delegate,metadata.getPackagings());
  singleSelect(delegate,metadata.getJavaVersions());
  singleSelect(delegate,metadata.getLanguages());
  singleSelect(delegate,metadata.getBootVersions());
  text(delegate,metadata.getGroupId());
  text(delegate,metadata.getArtifactId());
  text(delegate,metadata.getVersion());
  text(delegate,metadata.getName());
  text(delegate,metadata.getDescription());
  text(delegate,metadata.getPackageName());
  return delegate.toString();
}
