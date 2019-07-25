@Override public void customize(MavenBuild build){
  build.setName(this.projectDescription.getName());
  build.setDescription(this.projectDescription.getDescription());
  build.setProperty("java.version",this.projectDescription.getLanguage().jvmVersion());
  build.plugin("org.springframework.boot","spring-boot-maven-plugin");
  Maven maven=this.metadata.getConfiguration().getEnv().getMaven();
  String springBootVersion=this.projectDescription.getPlatformVersion().toString();
  ParentPom parentPom=maven.resolveParentPom(springBootVersion);
  if (parentPom.isIncludeSpringBootBom()) {
    String versionProperty="spring-boot.version";
    BillOfMaterials springBootBom=MetadataBuildItemMapper.toBom(this.metadata.createSpringBootBom(springBootVersion,versionProperty));
    if (!hasBom(build,springBootBom)) {
      build.addInternalVersionProperty(versionProperty,springBootVersion);
      build.boms().add("spring-boot",springBootBom);
    }
  }
  if (!maven.isSpringBootStarterParent(parentPom)) {
    build.setProperty("project.build.sourceEncoding","UTF-8");
    build.setProperty("project.reporting.outputEncoding","UTF-8");
  }
  build.parent(parentPom.getGroupId(),parentPom.getArtifactId(),parentPom.getVersion());
}
