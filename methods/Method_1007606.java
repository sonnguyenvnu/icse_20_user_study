public static void register(CommandRegistrationHandler registration,CommandManager commandManager,CommandManagerService commandManagerService){
  CommandManager collect=commandManagerService.newCommandManager();
  registration.register(collect,ExpandCommandsRegistration.builder(),new ExpandCommands());
  Command expandBaseCommand=collect.getCommand("/expand").orElseThrow(() -> new IllegalStateException("No /expand command"));
  commandManager.register("/expand",command -> {
    command.condition(new PermissionCondition(ImmutableSet.of("worldedit.selection.expand")));
    command.addPart(SubCommandPart.builder(TranslatableComponent.of("vert"),TextComponent.of("Vertical expansion sub-command")).withCommands(ImmutableSet.of(createVertCommand(commandManager))).optional().build());
    command.addParts(expandBaseCommand.getParts());
    command.action(expandBaseCommand.getAction());
    command.description(expandBaseCommand.getDescription());
  }
);
}
