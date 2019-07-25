@Override public boolean accept(final File file){
  return extensions == null || extensions.contains(FileUtils.getFileExtPart(file.getName(),false));
}
