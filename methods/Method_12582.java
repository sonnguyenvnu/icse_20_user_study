private List<String> readLines(InputStream input){
  try (BufferedReader buffer=new BufferedReader(new InputStreamReader(input,StandardCharsets.UTF_8))){
    return buffer.lines().map(String::trim).filter(StringUtils::hasText).collect(Collectors.toList());
  }
 catch (  IOException e) {
    log.warn("Couldn't read routes from",e);
    return Collections.emptyList();
  }
}
