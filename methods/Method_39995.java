protected boolean validateTypeOption(CommandOption option){
  typeCommandOption=option;
  return option.getArgs().length < 2;
}
