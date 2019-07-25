public static void help(List<String> commandPath,int page,boolean listSubCommands,WorldEdit we,Actor actor) throws InvalidComponentException {
  CommandManager manager=we.getPlatformManager().getPlatformCommandManager().getCommandManager();
  if (commandPath.isEmpty()) {
    printCommands(page,manager.getAllCommands(),actor,ImmutableList.of());
    return;
  }
  List<Command> visited=new ArrayList<>();
  Command currentCommand=detectCommand(manager,commandPath.get(0));
  if (currentCommand == null) {
    actor.printError(String.format("The command '%s' could not be found.",commandPath.get(0)));
    return;
  }
  visited.add(currentCommand);
  for (int i=1; i < commandPath.size(); i++) {
    String subCommand=commandPath.get(i);
    Map<String,Command> subCommands=getSubCommands(currentCommand);
    if (subCommands.isEmpty()) {
      actor.printError(String.format("'%s' has no sub-commands. (Maybe '%s' is for a parameter?)",toCommandString(visited),subCommand));
      CommandUsageBox box=new CommandUsageBox(visited,visited.stream().map(Command::getName).collect(Collectors.joining(" ")));
      actor.print(box.create());
      return;
    }
    if (subCommands.containsKey(subCommand)) {
      currentCommand=subCommands.get(subCommand);
      visited.add(currentCommand);
    }
 else {
      actor.printError(String.format("The sub-command '%s' under '%s' could not be found.",subCommand,toCommandString(visited)));
      printCommands(page,getSubCommands(Iterables.getLast(visited)).values().stream(),actor,visited);
      return;
    }
  }
  Map<String,Command> subCommands=getSubCommands(currentCommand);
  if (subCommands.isEmpty() || !listSubCommands) {
    CommandUsageBox box=new CommandUsageBox(visited,toCommandString(visited));
    actor.print(box.create());
  }
 else {
    printCommands(page,subCommands.values().stream(),actor,visited);
  }
}
