protected boolean validateVersionOption(CommandOption option){
  versionCommandOption=option;
  return option.getArgs().length == 1;
}
