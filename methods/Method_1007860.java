public static void register(CommandDispatcher<CommandSource> dispatcher,org.enginehub.piston.Command command){
  ImmutableList.Builder<String> aliases=ImmutableList.builder();
  aliases.add(command.getName()).addAll(command.getAliases());
  for (  String alias : aliases.build()) {
    LiteralArgumentBuilder<CommandSource> base=literal(alias).executes(FAKE_COMMAND).then(argument("args",StringArgumentType.greedyString()).suggests(CommandWrapper::suggest).executes(FAKE_COMMAND));
    if (command.getCondition() != org.enginehub.piston.Command.Condition.TRUE) {
      base.requires(requirementsFor(command));
    }
    dispatcher.register(base);
  }
}
