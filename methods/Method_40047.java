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
  long resolved=Analyzer.self.resolved.size();
  long unresolved=Analyzer.self.unresolved.size();
  sb.append("\n- resolved names: " + resolved);
  sb.append("\n- unresolved names: " + unresolved);
  sb.append("\n- name resolve rate: " + _.percent(resolved,resolved + unresolved));
  sb.append("\n" + _.getGCStats());
  return sb.toString();
}
