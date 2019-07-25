@ExceptionMatch public void convert(FilenameResolutionException e) throws CommandException {
  throw newCommandException("File '" + e.getFilename() + "' resolution error: " + e.getMessage(),e);
}
