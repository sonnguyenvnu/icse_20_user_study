private boolean buildFeatureFromTempl(List<Integer> feature,List<String> templs,int curPos,TaggerImpl tagger){
  for (  String tmpl : templs) {
    String featureID=applyRule(tmpl,curPos,tagger);
    if (featureID == null || featureID.length() == 0) {
      System.err.println("format error");
      return false;
    }
    int id=getID(featureID);
    if (id != -1) {
      feature.add(id);
    }
  }
  return true;
}
