void reset(){
  int i;
  for (i=0; i != coilCount; i++)   coilCurrents[i]=coilCurSourceValues[i]=coilCurCounts[i]=0;
  for (i=0; i != nodeCount; i++)   volts[i]=nodeCurrents[i]=nodeCurCounts[i]=0;
}
