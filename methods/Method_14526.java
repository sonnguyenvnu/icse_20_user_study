@JsonIgnore public ReconCandidate getBestCandidate(){
  if (candidates != null && candidates.size() > 0) {
    return candidates.get(0);
  }
  return null;
}
