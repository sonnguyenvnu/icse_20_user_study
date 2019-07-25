private static CompletableFuture<UserContext> login(String username,UserWithRoot userWithRoot,Pair<Multihash,CborObject> pair,NetworkAccess network,Crypto crypto,Consumer<String> progressCallback){
  try {
    progressCallback.accept("Logging in");
    WriterData userData=WriterData.fromCbor(pair.right);
    return createOurFileTreeOnly(username,userWithRoot.getRoot(),userData,network,crypto).thenCompose(root -> TofuCoreNode.load(username,root,network,crypto.random).thenCompose(keystore -> {
      TofuCoreNode tofu=new TofuCoreNode(network.coreNode,keystore);
      SigningPrivateKeyAndPublicHash signer=new SigningPrivateKeyAndPublicHash(userData.controller,userWithRoot.getUser().secretSigningKey);
      return buildTransactionService(root,username,network,crypto).thenCompose(transactions -> {
        UserContext result=new UserContext(username,signer,userWithRoot.getBoxingPair(),userWithRoot.getRoot(),network.withCorenode(tofu),crypto,new CommittedWriterData(MaybeMultihash.of(pair.left),userData),root,transactions);
        tofu.setContext(result);
        return result.getUsernameClaimExpiry().thenCompose(expiry -> expiry.isBefore(LocalDate.now().plusMonths(1)) ? result.renewUsernameClaim(LocalDate.now().plusMonths(2)) : CompletableFuture.completedFuture(true)).thenCompose(x -> {
          System.out.println("Initializing context..");
          return result.init(progressCallback);
        }
).exceptionally(Futures::logAndThrow);
      }
);
    }
));
  }
 catch (  Throwable t) {
    throw new IllegalStateException("Incorrect password");
  }
}
