@Nullable synchronized public <T extends ServiceParserInterface>T parser(Class<T> serviceParser){
  Symfony2ProjectComponent symfony2ProjectComponent=this.project.getComponent(Symfony2ProjectComponent.class);
  Collection<File> settingsServiceFiles=symfony2ProjectComponent.getContainerFiles();
  if (this.serviceParserInstance != null && !this.isModified(settingsServiceFiles)) {
    return (T)this.serviceParserInstance;
  }
  try {
    this.serviceParserInstance=serviceParser.newInstance();
    Symfony2ProjectComponent.getLogger().info("new instance: " + serviceParser.getName());
  }
 catch (  InstantiationException|IllegalAccessException ignored) {
  }
  if (this.serviceParserInstance != null) {
    if (this.extensions.size() > 0) {
      CompiledServiceBuilderArguments args=new CompiledServiceBuilderArguments(project);
      for (      CompiledServiceBuilderFactory.Builder builder : this.extensions) {
        builder.build(args);
      }
      for (      InputStream inputStream : args.getStreams()) {
        this.serviceParserInstance.parser(inputStream);
      }
    }
    this.serviceFiles=new HashMap<>();
    for (    File settingsServiceFile : settingsServiceFiles) {
      if (!settingsServiceFile.exists()) {
        continue;
      }
      try {
        this.serviceParserInstance.parser(new FileInputStream(settingsServiceFile));
      }
 catch (      FileNotFoundException e) {
        continue;
      }
      serviceFiles.put(settingsServiceFile.getAbsolutePath(),settingsServiceFile.lastModified());
    }
  }
  Symfony2ProjectComponent.getLogger().info("update: " + serviceParser.getName());
  return (T)this.serviceParserInstance;
}
