/** 
 * Returns the id of the reconciled item
 */
@Override @JsonProperty("id") public String getId(){
  if (isMatched()) {
    return _recon.match.id;
  }
 else   if (ET_ITEM.equals(getEntityType())) {
    return "Q" + getReconInternalId();
  }
 else   if (ET_PROPERTY.equals(getEntityType())) {
    return "P" + getReconInternalId();
  }
  return null;
}
