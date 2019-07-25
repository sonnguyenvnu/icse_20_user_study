private void execute(FormatterFactory formatterFactory) throws MojoExecutionException {
  List<File> files=collectFiles(formatterFactory);
  try (Formatter formatter=formatterFactory.newFormatter(files,getFormatterConfig())){
    process(files,formatter);
  }
 }
