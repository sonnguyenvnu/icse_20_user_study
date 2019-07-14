private List<Pattern> toPatterns(List<String> routes){
  return routes.stream().map(r -> "^" + r.replaceAll("/[*][*]","(/.*)?") + "$").map(Pattern::compile).collect(Collectors.toList());
}
