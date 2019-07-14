private boolean consumeOptionWithLongName(final String input,final String valueToConsume){
  for (  final Option option : options) {
    if (input.equals(option.longName)) {
      if (option.hasArg && valueToConsume == null) {
        throw new CliException("Option value not provided for: " + input);
      }
      option.consumer.accept(option.hasArg ? valueToConsume : input);
      return option.hasArg;
    }
    if (option.longName != null && input.startsWith(option.longName + "=")) {
      option.consumer.accept(input.substring(option.longName.length() + 1));
      return false;
    }
  }
  throw new CliException("Unknown option: " + input);
}
