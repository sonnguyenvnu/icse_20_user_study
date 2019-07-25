@Override public Iterable<String> suggest(){
  return concat(transform(group.getCommands(),CommandMetadata::getName),concat(transform(group.getOptions(),OptionMetadata::getOptions)));
}
