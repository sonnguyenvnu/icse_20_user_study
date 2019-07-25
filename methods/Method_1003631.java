private static Factory<BasicFileAttributes> getter(Path file){
  return () -> {
    if (Files.exists(file)) {
      return Files.readAttributes(file,BasicFileAttributes.class);
    }
 else {
      return null;
    }
  }
;
}
