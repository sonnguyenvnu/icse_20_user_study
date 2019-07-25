@ExceptionMatch public void convert(MaxRadiusException e) throws CommandException {
  throw newCommandException("Maximum radius (in configuration): " + worldEdit.getConfiguration().maxRadius,e);
}
