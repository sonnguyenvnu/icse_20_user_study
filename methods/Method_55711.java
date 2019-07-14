private static boolean workaroundJDK8195129(Path file){
  return Platform.get() == Platform.WINDOWS && file.toString().endsWith(".dll");
}
