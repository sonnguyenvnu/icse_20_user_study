private CandidateSet reduce(CandidateSet set,int numCandidates){
  if (set.candidates.length > numCandidates) {
    Candidate[] candidates=set.candidates;
    Arrays.sort(candidates,(left,right) -> Double.compare(right.score,left.score));
    Candidate[] newSet=new Candidate[numCandidates];
    System.arraycopy(candidates,0,newSet,0,numCandidates);
    set.candidates=newSet;
  }
  return set;
}
