public ModelPageProcessor addPageModel(Class clazz){
  PageModelExtractor pageModelExtractor=PageModelExtractor.create(clazz);
  pageModelExtractorList.add(pageModelExtractor);
  return this;
}
