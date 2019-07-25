@Override public Iterable<String> suggest(){
  return concat(transform(metadata.getCommandGroups(),CommandGroupMetadata::getName),transform(metadata.getDefaultGroupCommands(),CommandMetadata::getName),concat(transform(metadata.getOptions(),OptionMetadata::getOptions)));
}
