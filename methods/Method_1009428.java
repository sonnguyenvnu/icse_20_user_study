public static Stream<Path> walk(Path path,int depth){
  try {
    return Files.walk(path,depth);
  }
 catch (  Exception e) {
    logger.warn("Problem occured while walking path {}",path);
  }
  return Stream.empty();
}
