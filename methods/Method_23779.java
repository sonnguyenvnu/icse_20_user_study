public float[] getFloatColumn(int col){
  float[] outgoing=new float[rowCount];
  for (int row=0; row < rowCount; row++) {
    outgoing[row]=getFloat(row,col);
  }
  return outgoing;
}
