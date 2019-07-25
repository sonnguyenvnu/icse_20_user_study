/** 
 * @param newFilename
 * @param parent
 * @param overwrite
 * @param userContext
 * @return the updated parent
 */
public CompletableFuture<FileWrapper> rename(String newFilename,FileWrapper parent,boolean overwrite,UserContext userContext){
  setModified();
  if (!isLegalName(newFilename))   return CompletableFuture.completedFuture(parent);
  CompletableFuture<Optional<FileWrapper>> childExists=parent == null ? CompletableFuture.completedFuture(Optional.empty()) : parent.getDescendentByPath(newFilename,userContext.network);
  return childExists.thenCompose(existing -> {
    if (existing.isPresent() && !overwrite)     throw new IllegalStateException("Cannot rename, child already exists with name: " + newFilename);
    return ((overwrite && existing.isPresent()) ? existing.get().remove(parent,userContext) : CompletableFuture.completedFuture(parent)).thenCompose(res -> {
      AbsoluteCapability relativeCapability=pointer.capability;
      SymmetricKey baseKey=relativeCapability.rBaseKey;
      CryptreeNode fileAccess=pointer.fileAccess;
      boolean isDir=this.isDirectory();
      SymmetricKey key=isDir ? fileAccess.getParentKey(baseKey) : baseKey;
      FileProperties currentProps=fileAccess.getProperties(key);
      FileProperties newProps=new FileProperties(newFilename,isDir,currentProps.mimeType,currentProps.size,currentProps.modified,currentProps.isHidden,currentProps.thumbnail);
      SigningPrivateKeyAndPublicHash signer=signingPair();
      return userContext.network.synchronizer.applyComplexUpdate(owner(),signer,(s,committer) -> fileAccess.updateProperties(s,committer,writableFilePointer(),entryWriter,newProps,userContext.network)).thenApply(newVersion -> res.withVersion(newVersion));
    }
);
  }
);
}
