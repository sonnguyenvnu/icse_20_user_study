@Override public boolean validate(String command){
  return new PluginCommand(command).isValidate();
}
