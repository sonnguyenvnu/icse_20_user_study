@JsonIgnore public boolean isMatched(){
  return Recon.Judgment.Matched.equals(_recon.judgment) && _recon.match != null;
}
