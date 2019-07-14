@Override public boolean validate(){
  boolean invalid=super.validate();
  invalid=invalid && commandOptions.length == 1 && nameCommandOption == null && versionCommandOption == null && typeCommandOption.getArgs().length == 1;
  return invalid;
}
