@Override public boolean validate(){
  boolean invalid=super.validate();
  invalid=invalid && commandOptions.length == 3 && nameCommandOption != null && versionCommandOption != null && typeCommandOption.getArgs().length == 0;
  return invalid;
}
