@Override public InputStream open(String path) throws FileNotFoundException {
  return new FileInputStream(new File(baseFolder,path.replace(SEPARATOR_CHAR,File.separatorChar)));
}
