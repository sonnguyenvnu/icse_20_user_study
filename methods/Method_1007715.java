@ExceptionMatch public void convert(MaxBrushRadiusException e) throws CommandException {
  throw newCommandException("Maximum brush radius (in configuration): " + worldEdit.getConfiguration().maxBrushRadius,e);
}
