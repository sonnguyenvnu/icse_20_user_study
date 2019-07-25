public static void help(GlobalMetadata global,List<String> commandNames,StringBuilder out){
  if (commandNames.isEmpty()) {
    new GlobalUsageSummary().usage(global,out);
    return;
  }
  String name=commandNames.get(0);
  if (name.equals(global.getName())) {
    new GlobalUsage().usage(global,out);
    return;
  }
  for (  CommandMetadata command : global.getDefaultGroupCommands()) {
    if (name.equals(command.getName())) {
      new CommandUsage().usage(global.getName(),null,command.getName(),command,out);
      return;
    }
  }
  for (  CommandGroupMetadata group : global.getCommandGroups()) {
    if (name.endsWith(group.getName())) {
      if (commandNames.size() == 1) {
        new CommandGroupUsage().usage(global,group,out);
        return;
      }
 else {
        String commandName=commandNames.get(1);
        for (        CommandMetadata command : group.getCommands()) {
          if (commandName.equals(command.getName())) {
            new CommandUsage().usage(global.getName(),group.getName(),command.getName(),command,out);
            return;
          }
        }
        System.out.println("Unknown command " + name + " " + commandName);
      }
    }
  }
  System.out.println("Unknown command " + name);
}
