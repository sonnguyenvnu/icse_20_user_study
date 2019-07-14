public static PageModelExtractor create(Class clazz){
  PageModelExtractor pageModelExtractor=new PageModelExtractor();
  pageModelExtractor.init(clazz);
  return pageModelExtractor;
}
