@Override @SuppressWarnings("unchecked") public void run(@Nonnull final String... args){
  new JCommander(this).parse(args);
  final KyloCatalogReader reader=client.read().format(sourceFormat).addFiles(sourceFiles).addJars(sourceJars).options(sourceOptions);
  final Object source=(sourcePath != null) ? reader.load(sourcePath) : reader.load();
  final KyloCatalogWriter writer=client.write(source).format(targetFormat).addFiles(targetFiles).addJars(targetJars).options(targetOptions);
  if (targetPath != null) {
    writer.save(targetPath);
  }
 else {
    writer.save();
  }
}
