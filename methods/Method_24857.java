private static List<String> sanitizeClassPath(String classPathString){
  return Arrays.stream(classPathString.split(File.pathSeparator)).filter(p -> p != null && !p.trim().isEmpty()).distinct().collect(Collectors.toList());
}
