@ExceptionMatch public void convert(MaxChangedBlocksException e) throws CommandException {
  throw newCommandException("Max blocks changed in an operation reached (" + e.getBlockLimit() + ").",e);
}
