private boolean consumeOptionWithShortName(final String input,final String valueToConsume){
  for (  final Option option : options) {
    if (input.equals(option.shortName)) {
      if (option.hasArg) {
        if (valueToConsume == null) {
          throw new CliException("Option value not provided for: " + input);
        }
        option.consumer.accept(valueToConsume);
        return true;
      }
 else {
        option.consumer.accept(input);
        return false;
      }
    }
    if (option.shortName != null && input.startsWith(option.shortName + "=")) {
      option.consumer.accept(input.substring(option.shortName.length() + 1));
      return false;
    }
  }
  throw new CliException("Unknown option: " + input);
}
