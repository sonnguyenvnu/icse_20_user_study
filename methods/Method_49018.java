public long[] getLongRepresentation(){
  long[] r=new long[3 + (inVertexId != 0 ? 1 : 0)];
  r[0]=relationId;
  r[1]=outVertexId;
  r[2]=typeId;
  if (inVertexId != 0)   r[3]=inVertexId;
  return r;
}
