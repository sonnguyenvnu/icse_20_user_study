@ExceptionMatch public void convert(InsufficientArgumentsException e) throws CommandException {
  throw newCommandException(e.getMessage(),e);
}
