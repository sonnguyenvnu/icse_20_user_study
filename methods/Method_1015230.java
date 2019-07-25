private Rope insert(final int index,Iterator<byte[]> chunks,int numCodeUnits){
  if (index < 0 || index > size()) {
    throw new IndexOutOfBoundsException();
  }
  Object editor=isLinear() ? this.editor : new Object();
  Node newRoot=null;
  if (numCodeUnits < MAX_CHUNK_CODE_UNITS) {
    newRoot=root.update(0,index,editor,(offset,chunk) -> {
      if (numCodeUnits + UnicodeChunk.numCodeUnits(chunk) <= MAX_CHUNK_CODE_UNITS) {
        byte[] newChunk=UnicodeChunk.slice(chunk,0,index - offset);
        while (chunks.hasNext()) {
          newChunk=UnicodeChunk.concat(newChunk,chunks.next());
        }
        return UnicodeChunk.concat(newChunk,UnicodeChunk.slice(chunk,index - offset,UnicodeChunk.numCodePoints(chunk)));
      }
 else {
        return null;
      }
    }
);
  }
  if (newRoot == null) {
    newRoot=root.slice(0,index,editor);
    while (chunks.hasNext()) {
      newRoot=newRoot.pushLast(chunks.next(),editor);
    }
    newRoot=newRoot.concat(root.slice(index,size(),editor),editor);
  }
  if (isLinear()) {
    root=newRoot;
    return this;
  }
 else {
    return new Rope(newRoot,false);
  }
}
