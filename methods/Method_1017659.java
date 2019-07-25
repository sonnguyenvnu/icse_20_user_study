public void transform(float a,float b,float c,float d,float e,float f){
  if (!isValid()) {
    return;
  }
  matrix=TransformUtils.preConcat(matrix,new float[]{a,b,c,d,e,f});
  if (pathData == null) {
    return;
  }
  PathDataNode[] node=PathDataNode.createNodesFromPathData(pathData);
  if (node == null) {
    return;
  }
  if (!(matrix[0] == 1.0f && matrix[1] == 0.0f && matrix[2] == 0.0f && matrix[3] == 1.0f && matrix[4] == 0.0f && matrix[5] == 0.0f)) {
    PathDataNode.transform(matrix[0],matrix[1],matrix[2],matrix[3],matrix[4],matrix[5],node);
  }
  pathData=PathDataNode.nodeListToString(node);
}
