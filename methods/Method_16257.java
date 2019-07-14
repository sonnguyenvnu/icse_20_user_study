@Override public boolean support(DashBoardConfigEntity entity){
  return StringUtils.hasText(entity.getScriptLanguage()) && StringUtils.hasText(entity.getScript()) && supportLang.contains(entity.getScriptLanguage());
}
