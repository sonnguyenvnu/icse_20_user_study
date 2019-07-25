@ExceptionMatch public void convert(InvalidItemException e) throws CommandException {
  throw newCommandException(e.getMessage(),e);
}
