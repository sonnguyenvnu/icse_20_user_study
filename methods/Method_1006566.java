public String render(Help help){
  CommandSpec spec=help.commandSpec();
  if (spec.subcommands().isEmpty()) {
    return "";
  }
  TextTable textTable=TextTable.forColumns(help.ansi(),new Column(15,2,Overflow.SPAN),new Column(spec.usageMessage().width() - 15,2,Overflow.WRAP));
  textTable.setAdjustLineBreaksForWideCJKCharacters(spec.usageMessage().adjustLineBreaksForWideCJKCharacters());
  for (  CommandLine subcommand : spec.subcommands().values()) {
    addHierarchy(subcommand,textTable,"");
  }
  return textTable.toString();
}
