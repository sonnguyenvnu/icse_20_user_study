protected boolean validateNameOption(CommandOption option){
  nameCommandOption=option;
  return option.getArgs().length == 1;
}
