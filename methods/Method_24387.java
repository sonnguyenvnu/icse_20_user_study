protected void updatePointIndexCache(){
  IndexCache cache=tessGeo.pointIndexCache;
  if (family == GROUP) {
    firstPointIndexCache=lastPointIndexCache=-1;
    int gindex=-1;
    for (int i=0; i < childCount; i++) {
      PShapeOpenGL child=(PShapeOpenGL)children[i];
      int first=child.firstPointIndexCache;
      int count=-1 < first ? child.lastPointIndexCache - first + 1 : -1;
      for (int n=first; n < first + count; n++) {
        if (gindex == -1) {
          gindex=cache.addNew(n);
          firstPointIndexCache=gindex;
        }
 else {
          if (cache.vertexOffset[gindex] == cache.vertexOffset[n]) {
            cache.incCounts(gindex,cache.indexCount[n],cache.vertexCount[n]);
          }
 else {
            gindex=cache.addNew(n);
          }
        }
      }
      if (-1 < child.firstPointVertex) {
        if (firstPointVertex == -1)         firstPointVertex=Integer.MAX_VALUE;
        firstPointVertex=PApplet.min(firstPointVertex,child.firstPointVertex);
      }
      if (-1 < child.lastPointVertex) {
        lastPointVertex=PApplet.max(lastPointVertex,child.lastPointVertex);
      }
    }
    lastPointIndexCache=gindex;
  }
 else {
    firstPointVertex=lastPointVertex=cache.vertexOffset[firstPointIndexCache];
    for (int n=firstPointIndexCache; n <= lastPointIndexCache; n++) {
      int ioffset=cache.indexOffset[n];
      int icount=cache.indexCount[n];
      int vcount=cache.vertexCount[n];
      if (PGL.MAX_VERTEX_INDEX1 <= root.pointVertexRel + vcount) {
        root.pointVertexRel=0;
        root.pointVertexOffset=root.pointVertexAbs;
        cache.indexOffset[n]=root.pointIndexOffset;
      }
 else {
        tessGeo.incPointIndices(ioffset,ioffset + icount - 1,root.pointVertexRel);
      }
      cache.vertexOffset[n]=root.pointVertexOffset;
      root.pointIndexOffset+=icount;
      root.pointVertexAbs+=vcount;
      root.pointVertexRel+=vcount;
      lastPointVertex+=vcount;
    }
    lastPointVertex--;
  }
}
