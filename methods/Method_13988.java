@Override @JsonProperty("siteIri") public String getSiteIri(){
  if (isMatched()) {
    return _recon.identifierSpace;
  }
 else {
    return EntityIdValue.SITE_LOCAL;
  }
}
