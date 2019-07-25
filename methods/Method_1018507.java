private static CorenodeState load(Path statePath) throws IOException {
  Logging.LOG().info("Reading state from " + statePath + " which exists ? " + Files.exists(statePath) + " from cwd " + System.getProperty("cwd"));
  byte[] data=Files.readAllBytes(statePath);
  CborObject object=CborObject.fromByteArray(data);
  return CorenodeState.fromCbor(object);
}
