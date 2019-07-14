private CommandOption[] parseCommandOption(String telnetInput){
  String[] phrases=telnetInput.split(Constants.SPACE_SPLIT);
  List<CommandOption> options=new ArrayList<>();
  if (phrases.length == 1) {
    return options.toArray(new CommandOption[]{});
  }
  CommandOption temp=new CommandOption();
  for (int i=1; i < phrases.length; ++i) {
    if (phrases[i].startsWith(CommandOption.OPTION_MARKER)) {
      if (StringUtils.isEmpty(temp.getOption()) && temp.getArgs().length != 0) {
        temp.setOption(CommandOption.INVALID_OPTION);
        options.add(temp);
        temp=new CommandOption();
      }
 else       if (!StringUtils.isEmpty(temp.getOption())) {
        options.add(temp);
        temp=new CommandOption();
      }
      temp.setOption(phrases[i]);
    }
 else {
      temp.addArgs(phrases[i]);
    }
  }
  if (temp.getArgs().length != 0 || !StringUtils.isEmpty(temp.getOption())) {
    if (StringUtils.isEmpty(temp.getOption())) {
      temp.setOption(CommandOption.INVALID_OPTION);
    }
    options.add(temp);
  }
  return options.toArray(new CommandOption[]{});
}
