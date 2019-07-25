public static Stream<Path> walk(Path path){
  try {
    return Files.walk(path);
  }
 catch (  Exception e) {
    logger.warn("Problem occured while walking path {}",path);
  }
  return Stream.empty();
}
