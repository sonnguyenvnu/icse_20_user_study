@Override public boolean validate(){
  boolean valid=super.validate();
  if (nameCommandOption == null && versionCommandOption != null) {
    return false;
  }
  valid=valid && (typeCommandOption.getArgs().length == 0) && (nameCommandOption == null || nameCommandOption.getArgs().length == 1) && (versionCommandOption == null || versionCommandOption.getArgs().length == 1);
  return valid;
}
