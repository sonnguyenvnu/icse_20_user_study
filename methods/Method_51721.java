private void addDeadLink(Map<Path,List<Future<String>>> fileToDeadLinks,Path file,Future<String> line){
  fileToDeadLinks.computeIfAbsent(file,k -> new ArrayList<>()).add(line);
}
