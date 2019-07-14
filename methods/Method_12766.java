private static Object getDexElements(Object pathList) throws Exception {
  return Reflector.with(pathList).field("dexElements").get();
}
