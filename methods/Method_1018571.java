public NetworkAccess clear(){
  WriteSynchronizer synchronizer=new WriteSynchronizer(mutable,dhtClient);
  MutableTree mutableTree=new MutableTreeImpl(mutable,dhtClient,synchronizer);
  return new NetworkAccess(coreNode,social,dhtClient,mutable,mutableTree,synchronizer,instanceAdmin,spaceUsage,usernames,isJavascript);
}
