@ExceptionMatch public void convert(InvalidFilenameException e) throws CommandException {
  throw newCommandException("Filename '" + e.getFilename() + "' invalid: " + e.getMessage(),e);
}
