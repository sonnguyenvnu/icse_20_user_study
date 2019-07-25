@Override public <K>K create(Class<K> clazz) throws Exception {
  try {
    return getBeanOrCreate(clazz);
  }
 catch (  Exception e) {
    logger.warn("Unable to get bean of class {}, using default Picocli factory",clazz);
    return CommandLine.defaultFactory().create(clazz);
  }
}
