@ExceptionMatch public void convert(WorldEditException e) throws CommandException {
  throw newCommandException(e.getMessage(),e);
}
