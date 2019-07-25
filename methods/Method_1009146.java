private void proceed(final Reporter reporter,final List<File> files,final boolean isSyntax) throws IOException, ProcessingException {
  final RetCode retCode=isSyntax ? doSyntax(reporter,files) : doValidation(reporter,files);
  System.exit(retCode.get());
}
