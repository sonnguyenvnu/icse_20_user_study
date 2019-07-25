private Iterator<GraphNode> iterator(){
  try {
    this.nodePtrRAF.seek(0);
    final long length=this.nodePtrRAF.length();
    return new Iterator<GraphNode>(){
      public boolean hasNext(){
        return nodePtrRAF.getFilePointer() < length;
      }
      public GraphNode next(){
        try {
          long fp=nodePtrRAF.readLong();
          int tidx=nodePtrRAF.readInt();
          long loc=nodePtrRAF.readLongNat();
          return getNodeFromDisk(fp,tidx,loc);
        }
 catch (        IOException e) {
          throw new RuntimeException(e);
        }
      }
      public void remove(){
        throw new UnsupportedOperationException("Not supported!");
      }
    }
;
  }
 catch (  IOException e1) {
    throw new RuntimeException(e1);
  }
}
