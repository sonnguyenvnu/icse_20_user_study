@ExceptionMatch public void convert(UnknownDirectionException e) throws CommandException {
  throw newCommandException("Unknown direction: " + e.getDirection(),e);
}
