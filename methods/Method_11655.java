private void initClassExtractors(){
  Annotation annotation=clazz.getAnnotation(TargetUrl.class);
  if (annotation == null) {
    targetUrlPatterns.add(Pattern.compile(".*"));
  }
 else {
    TargetUrl targetUrl=(TargetUrl)annotation;
    String[] value=targetUrl.value();
    for (    String s : value) {
      targetUrlPatterns.add(Pattern.compile(s.replace(".","\\.").replace("*","[^\"'#]*")));
    }
    if (!targetUrl.sourceRegion().equals("")) {
      targetUrlRegionSelector=new XpathSelector(targetUrl.sourceRegion());
    }
  }
  annotation=clazz.getAnnotation(HelpUrl.class);
  if (annotation != null) {
    HelpUrl helpUrl=(HelpUrl)annotation;
    String[] value=helpUrl.value();
    for (    String s : value) {
      helpUrlPatterns.add(Pattern.compile(s.replace(".","\\.").replace("*","[^\"'#]*")));
    }
    if (!helpUrl.sourceRegion().equals("")) {
      helpUrlRegionSelector=new XpathSelector(helpUrl.sourceRegion());
    }
  }
  annotation=clazz.getAnnotation(ExtractBy.class);
  if (annotation != null) {
    ExtractBy extractBy=(ExtractBy)annotation;
    objectExtractor=new Extractor(new XpathSelector(extractBy.value()),Extractor.Source.Html,extractBy.notNull(),extractBy.multi());
  }
}
