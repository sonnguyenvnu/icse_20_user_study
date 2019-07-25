public void modify(ObjectMapper mapper){
  mapper.disable(AUTO_DETECT_SETTERS);
  mapper.disable(AUTO_DETECT_GETTERS);
  VisibilityChecker<?> checker=mapper.getSerializationConfig().getDefaultVisibilityChecker();
  mapper.setVisibilityChecker(checker.withFieldVisibility(ANY));
}
