static Set<String> analyze(final String input){
  if (input.isEmpty()) {
    return ImmutableSet.of();
  }
  final String[] parts=p.split(input);
  final Set<String> output=new HashSet<>();
  for (  final String p : parts) {
    final String l=p.toLowerCase();
    if (l.length() == 0) {
      continue;
    }
    output.add(l);
    output.addAll(prefix(l));
  }
  return output;
}
