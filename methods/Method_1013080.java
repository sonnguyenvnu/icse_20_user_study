@Override public void fill(){
  completionHelper.getParentKeyName().ifPresent(parentKeyName -> getSecurityDefinitionByName(parentKeyName).forEach(this::addValue));
}
