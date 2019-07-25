@Override public Iterator<CandidateHost> iterator(){
  Iterator<CandidateHost> res=candidateIterator();
  if (!res.hasNext()) {
    List<HostSpec> allHosts=Arrays.asList(hostSpecs);
    if (loadBalance) {
      allHosts=new ArrayList<HostSpec>(allHosts);
      Collections.shuffle(allHosts);
    }
    res=withReqStatus(targetServerType,allHosts).iterator();
  }
  return res;
}
