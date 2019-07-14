private void assertConfigurationIsValid(){
  for (  final Option option : options) {
    if (option.consumer == null) {
      throw new CliException("Option has no registered consumer: " + option);
    }
  }
}
