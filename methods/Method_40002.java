public static Command createCommand(String commandMarker,CommandOption[] commandOptions){
switch (CommandType.getCommandType(commandMarker)) {
case INSTALL:
    return new InstallCommand(commandOptions);
case UNINSTALL:
  return new UninstallCommand(commandOptions);
case CHECK:
return new CheckCommand(commandOptions);
case SWITCH:
return new SwitchCommand(commandOptions);
default :
return null;
}
}
