@Override public boolean exists() throws BackendException {
  return Schema.instance.getKeyspaceInstance(keySpaceName) != null;
}
