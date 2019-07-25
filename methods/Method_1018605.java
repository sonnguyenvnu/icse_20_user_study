/** 
 * @param current
 * @param committer
 * @param network
 * @param crypto
 * @param parent
 * @return updated parent dir
 */
public CompletableFuture<Pair<FileWrapper,Snapshot>> clean(Snapshot current,Committer committer,NetworkAccess network,Crypto crypto,FileWrapper parent){
  if (!isDirty())   return CompletableFuture.completedFuture(new Pair<>(this,current));
  if (isDirectory()) {
    throw new IllegalStateException("Directories are never dirty (they are cleaned immediately)!");
  }
 else {
    return pointer.fileAccess.cleanAndCommit(current,committer,writableFilePointer(),signingPair(),SymmetricKey.random(),parent.getLocation(),parent.getParentKey(),network,crypto).thenApply(cwd -> {
      setModified();
      return new Pair<>(parent,cwd);
    }
);
  }
}
