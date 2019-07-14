/** 
 * Recomputes the features associated with this reconciliation object (only if we have at least one candidate).
 * @param text the cell value to compare the reconciliation data to
 */
public void computeFeatures(Recon recon,String text){
  if (recon.candidates != null && !recon.candidates.isEmpty()) {
    ReconCandidate candidate=recon.candidates.get(0);
    recon.setFeature(Recon.Feature_nameMatch,text.equalsIgnoreCase(candidate.name));
    recon.setFeature(Recon.Feature_nameLevenshtein,StringUtils.getLevenshteinDistance(StringUtils.lowerCase(text),StringUtils.lowerCase(candidate.name)));
    recon.setFeature(Recon.Feature_nameWordDistance,wordDistance(text,candidate.name));
    recon.setFeature(Recon.Feature_typeMatch,false);
    if (this.typeID != null) {
      for (      String typeID : candidate.types) {
        if (this.typeID.equals(typeID)) {
          recon.setFeature(Recon.Feature_typeMatch,true);
          break;
        }
      }
    }
  }
 else {
    recon.features=new Object[Recon.Feature_max];
  }
}
