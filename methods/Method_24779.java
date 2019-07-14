protected static List<CompletionCandidate> trimCandidates(String newWord,List<CompletionCandidate> candidates){
  ArrayList<CompletionCandidate> newCandidate=new ArrayList<>();
  newWord=newWord.toLowerCase();
  for (  CompletionCandidate comp : candidates) {
    if (comp.startsWith(newWord)) {
      newCandidate.add(comp);
    }
  }
  return newCandidate;
}
