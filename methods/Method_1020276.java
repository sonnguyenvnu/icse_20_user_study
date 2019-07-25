public void mapto(int x1,int y1,int x2,int y2,int x3,int y3){
  sourcem[0]=x1;
  sourcem[1]=x2;
  sourcem[2]=x3;
  sourcem[3]=y1;
  sourcem[4]=y2;
  sourcem[5]=y3;
  sourcem[6]=1;
  sourcem[7]=1;
  sourcem[8]=1;
  inverse(sourcem);
  clone(transm,uvm);
  matmul(transm,sourcem);
  transm[6]=0;
  transm[7]=0;
  transm[8]=1;
}
