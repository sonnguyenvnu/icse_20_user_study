private static int weight(final VirtualFile candidate,final String file){
  final String pattern=file.replace('\\','/');
  final String candidatePath=candidate.getPath();
  int weight=0;
  while (weight < pattern.length() && weight < candidatePath.length() && pattern.charAt(pattern.length() - weight - 1) == candidatePath.charAt(candidatePath.length() - weight - 1))   weight++;
  return weight;
}
