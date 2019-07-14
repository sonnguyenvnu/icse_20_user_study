public float[] getFloatRow(int row){
  float[] outgoing=new float[columns.length];
  for (int col=0; col < columns.length; col++) {
    outgoing[col]=getFloat(row,col);
  }
  return outgoing;
}
