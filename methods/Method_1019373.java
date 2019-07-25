private void init(Workspace workspace,String plaintext,EncryptionStrategy encryptionStrategy) throws Exception {
  this.workspace=workspace;
  setId(workspace.getId());
  setName(workspace.getName());
  setDescription(workspace.getDescription());
  setVersion(workspace.getVersion());
  setRevision(workspace.getRevision());
  setLastModifiedUser(workspace.getLastModifiedUser());
  setLastModifiedAgent(workspace.getLastModifiedAgent());
  this.plaintext=plaintext;
  this.ciphertext=encryptionStrategy.encrypt(plaintext);
  this.encryptionStrategy=encryptionStrategy;
}
