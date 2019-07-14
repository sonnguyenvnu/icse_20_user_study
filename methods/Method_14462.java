/** 
 * TODO - use JsonIdentityInfo on recon - implement custom resolver to tie it to a pool - figure it all out
 * @return
 */
@JsonProperty("r") @JsonInclude(Include.NON_NULL) public String getReconIdString(){
  if (recon != null) {
    return Long.toString(recon.id);
  }
  return null;
}
