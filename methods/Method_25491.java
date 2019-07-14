protected Set<String> enabled(){
  return Sets.difference(getAllChecks().keySet(),disabled());
}
