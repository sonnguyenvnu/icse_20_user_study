@ExceptionMatch public void convert(IncompleteRegionException e) throws CommandException {
  throw newCommandException("Make a region selection first.",e);
}
