public static ModelPageProcessor create(Site site,Class... clazzs){
  ModelPageProcessor modelPageProcessor=new ModelPageProcessor(site);
  for (  Class clazz : clazzs) {
    modelPageProcessor.addPageModel(clazz);
  }
  return modelPageProcessor;
}
