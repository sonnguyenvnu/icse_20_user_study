public int write(PeergosStat parent,String name,byte[] toWrite,long size,long offset){
  try {
    long updatedLength=size + offset;
    if (Integer.MAX_VALUE < updatedLength) {
      throw new IllegalStateException("Cannot write more than " + Integer.MAX_VALUE + " bytes");
    }
    FileWrapper b=parent.treeNode.uploadFileSection(name,new AsyncReader.ArrayBacked(toWrite),false,offset,offset + size,Optional.empty(),true,context.network,context.crypto,l -> {
    }
,parent.treeNode.generateChildLocationsFromSize(size,context.crypto.random)).get();
    return (int)size;
  }
 catch (  Throwable t) {
    LOG.log(Level.WARNING,t.getMessage(),t);
    return -ErrorCodes.ENOENT();
  }
}
