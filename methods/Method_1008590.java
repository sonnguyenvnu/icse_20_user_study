@Override public void verify(String seed,DiscoveryNode localNode){
  BlobContainer testBlobContainer=blobStore().blobContainer(basePath().add(testBlobPrefix(seed)));
  if (testBlobContainer.blobExists("master.dat")) {
    try {
      BytesArray bytes=new BytesArray(seed);
      try (InputStream stream=bytes.streamInput()){
        testBlobContainer.writeBlob("data-" + localNode.getId() + ".dat",stream,bytes.length());
      }
     }
 catch (    IOException exp) {
      throw new RepositoryVerificationException(metadata.name(),"store location [" + blobStore() + "] is not accessible on the node [" + localNode + "]",exp);
    }
  }
 else {
    throw new RepositoryVerificationException(metadata.name(),"a file written by master to the store [" + blobStore() + "] cannot be accessed on the node [" + localNode + "]. " + "This might indicate that the store [" + blobStore() + "] is not shared between this node and the master node or " + "that permissions on the store don't allow reading files written by the master node");
  }
}
