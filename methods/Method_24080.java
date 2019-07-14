protected void createPointBuffers(){
  if (!pointBuffersCreated || pointBuffersContextIsOutdated()) {
    pointBuffersContext=pgl.getCurrentContext();
    bufPointVertex=new VertexBuffer(this,PGL.ARRAY_BUFFER,3,PGL.SIZEOF_FLOAT);
    bufPointColor=new VertexBuffer(this,PGL.ARRAY_BUFFER,1,PGL.SIZEOF_INT);
    bufPointAttrib=new VertexBuffer(this,PGL.ARRAY_BUFFER,2,PGL.SIZEOF_FLOAT);
    pgl.bindBuffer(PGL.ARRAY_BUFFER,0);
    bufPointIndex=new VertexBuffer(this,PGL.ELEMENT_ARRAY_BUFFER,1,PGL.SIZEOF_INDEX,true);
    pgl.bindBuffer(PGL.ELEMENT_ARRAY_BUFFER,0);
    pointBuffersCreated=true;
  }
}
