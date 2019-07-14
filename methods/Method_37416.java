private void consumeOptionWithShortNameAndNoArguments(final String shortName){
  for (  final Option option : options) {
    if (shortName.equals(option.shortName) && !option.hasArg) {
      option.consumer.accept(shortName);
      return;
    }
  }
  throw new CliException("Unknown option: " + shortName);
}
