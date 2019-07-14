@Override public Recon createNewRecon(long historyEntryID){
  Recon recon=new Recon(historyEntryID,identifierSpace,schemaSpace);
  recon.service=service;
  return recon;
}
