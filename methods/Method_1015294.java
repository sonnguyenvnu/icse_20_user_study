private void validate(ParseState state){
  CommandMetadata command=state.getCommand();
  if (command == null) {
    List<String> unparsedInput=state.getUnparsedInput();
    if (unparsedInput.isEmpty()) {
      throw new ParseCommandMissingException();
    }
 else {
      throw new ParseCommandUnrecognizedException(unparsedInput);
    }
  }
  ArgumentsMetadata arguments=command.getArguments();
  if (state.getParsedArguments().isEmpty() && arguments != null && arguments.isRequired()) {
    throw new ParseArgumentsMissingException(arguments.getTitle());
  }
  if (!state.getUnparsedInput().isEmpty()) {
    throw new ParseArgumentsUnexpectedException(state.getUnparsedInput());
  }
  if (state.getLocation() == Context.OPTION) {
    throw new ParseOptionMissingValueException(state.getCurrentOption().getTitle());
  }
  for (  OptionMetadata option : command.getAllOptions()) {
    if (option.isRequired() && !state.getParsedOptions().containsKey(option)) {
      throw new ParseOptionMissingException(option.getOptions().iterator().next());
    }
  }
}
