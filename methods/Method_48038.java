@Override public boolean exists() throws BackendException {
  return !environment.getDatabaseNames().isEmpty();
}
