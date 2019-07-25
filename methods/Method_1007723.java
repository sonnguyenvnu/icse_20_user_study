@ExceptionMatch public void convert(InvalidToolBindException e) throws CommandException {
  throw newCommandException("Can't bind tool to " + e.getItemType().getName() + ": " + e.getMessage(),e);
}
