public CharSequence pipe(URI uri) throws java.io.FileNotFoundException, java.io.IOException {
  if (!uri.getScheme().equals("file"))   throw new UnsupportedOperationException("Only file: scheme implemented.");
  return pipe(new File(uri.getPath()));
}
