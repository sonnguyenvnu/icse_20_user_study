public static String apiFindLibrary(String start,String name){
  String libName=Platform.get().mapLibraryName(name);
  try (Stream<Path> paths=Files.find(Paths.get(start).toAbsolutePath(),Integer.MAX_VALUE,(path,attributes) -> attributes.isRegularFile() && path.getFileName().toString().equals(libName))){
    return paths.findFirst().map(Path::toString).orElse(name);
  }
 catch (  IOException e) {
    return name;
  }
}
