@Override public boolean canHandle(@NonNull File file) throws IOException {
  for (  AbstractImporter importer : importers)   if (importer.canHandle(file))   return true;
  return false;
}
