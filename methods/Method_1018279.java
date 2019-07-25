@Override public boolean validate(String command){
  return new BizCommand(command).isValidate();
}
