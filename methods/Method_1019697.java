private void register(ITemplateEngine templateEngine){
  templateEnginesCache.put(templateEngine.getKind(),templateEngine);
}
