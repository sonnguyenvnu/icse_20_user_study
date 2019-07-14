private ModuleType parseAndResolve(String file) throws Exception {
  finer("Indexing: " + file);
  progress.tick();
  return parseAndResolve(file,null);
}
