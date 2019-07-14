@NotNull public String getAnalysisSummary(){
  StringBuilder sb=new StringBuilder();
  sb.append("\n" + _.banner("analysis summary"));
  String duration=_.formatTime(System.currentTimeMillis() - stats.getInt("startTime"));
  sb.append("\n- total time: " + duration);
  sb.append("\n- modules loaded: " + loadedFiles.size());
  sb.append("\n- semantic problems: " + semanticErrors.size());
  sb.append("\n- failed to parse: " + failedToParse.size());
  int nDef=0, nXRef=0;
  for (  Binding b : getAllBindings()) {
    nDef+=1;
    nXRef+=b.refs.size();
  }
  sb.append("\n- number of definitions: " + nDef);
  sb.append("\n- number of cross references: " + nXRef);
  sb.append("\n- number of references: " + getReferences().size());
  long nResolved=this.resolved.size();
  long nUnresolved=this.unresolved.size();
  sb.append("\n- resolved names: " + nResolved);
  sb.append("\n- unresolved names: " + nUnresolved);
  sb.append("\n- name resolve rate: " + _.percent(nResolved,nResolved + nUnresolved));
  sb.append("\n" + _.getGCStats());
  return sb.toString();
}
