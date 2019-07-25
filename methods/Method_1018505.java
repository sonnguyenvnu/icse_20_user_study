/** 
 * Update the existing mappings based on the diff between the current champ and the champ with the supplied root.
 * @param newRoot The root of the new champ
 */
private synchronized void update(MaybeMultihash newRoot){
  updateAllMappings(signer.publicKeyHash,currentRoot,newRoot,ipfs,chains,reverseLookup,usernames);
  this.currentRoot=newRoot;
}
