@PostConstruct public void init(){
  CompletableFuture.runAsync(() -> {
    String appName=env.getProperty("spring.application.name","");
    try {
      JarDependencies dependencies=Analyzer.getAllPomInfo();
      cache.put("appName",nullToEmpty(appName));
      cache.put("springBootVersion",nullToEmpty(dependencies.getSpringBootVersion()));
      cache.put("springCloudVersion",nullToEmpty(dependencies.getSpringCloudVersion()));
      List<HashMap<String,String>> list=new ArrayList<>();
      dependencies.getPomInfos().forEach(p -> {
        if (p.getGroupId().equals("org.springframework.cloud") && p.getArtifactId().startsWith("spring-cloud")) {
          HashMap<String,String> kv=new HashMap<>();
          kv.put(p.getArtifactId(),p.getVersion());
          list.add(kv);
        }
      }
);
      cache.put("using",list);
    }
 catch (    Exception e) {
      logger.error(e.getMessage(),e);
    }
  }
);
}
