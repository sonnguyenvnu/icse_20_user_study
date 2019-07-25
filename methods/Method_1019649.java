public void initialize(ITemplateEngine templateEngine){
  templateEngine.setTemplateCacheInfoProvider(XDocReportRegistry.getRegistry());
}
