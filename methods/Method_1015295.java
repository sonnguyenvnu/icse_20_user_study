@Override public Iterable<String> suggest(){
  ImmutableList.Builder<String> suggestions=ImmutableList.<String>builder().addAll(concat(transform(command.getCommandOptions(),OptionMetadata::getOptions)));
  if (command.getArguments() != null) {
    suggestions.add("--");
  }
  return suggestions.build();
}
