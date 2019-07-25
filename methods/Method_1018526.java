public int truncate(PeergosStat parent,PeergosStat file,long size){
  debug("TRUNCATE file %s, size %d",file.properties.name,size);
  try {
    if (size > Integer.MAX_VALUE)     throw new IllegalStateException("Trying to truncate/extend to > 4GiB! " + size);
    byte[] original=new byte[(int)file.properties.size];
    Serialize.readFullArray(file.treeNode.getInputStream(context.network,context.crypto.random,l -> {
    }
).get(),original);
    byte[] truncated=Arrays.copyOfRange(original,0,(int)size);
    FileWrapper newParent=file.treeNode.remove(parent.treeNode,context).get();
    FileWrapper b=newParent.uploadOrOverwriteFile(file.properties.name,new AsyncReader.ArrayBacked(truncated),truncated.length,context.network,context.crypto,l -> {
    }
,newParent.generateChildLocationsFromSize(truncated.length,context.crypto.random)).get();
    return (int)size;
  }
 catch (  Throwable t) {
    LOG.log(Level.WARNING,t.getMessage(),t);
    return -ErrorCodes.ENOENT();
  }
}
