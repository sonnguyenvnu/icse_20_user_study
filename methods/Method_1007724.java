@ExceptionMatch public void convert(FileSelectionAbortedException e) throws CommandException {
  throw newCommandException("File selection aborted.",e);
}
