public void translatexi(int X,int Y,int Z){
  double x=(X / 65536.0);
  double y=(Y / 65536.0);
  double z=(Z / 65536.0);
  tempt[0]=1;
  tempt[1]=0;
  tempt[2]=0;
  tempt[3]=0;
  tempt[4]=0;
  tempt[5]=1;
  tempt[6]=0;
  tempt[7]=0;
  tempt[8]=0;
  tempt[9]=0;
  tempt[10]=1;
  tempt[11]=0;
  tempt[12]=x;
  tempt[13]=y;
  tempt[14]=z;
  tempt[15]=1;
  matmul(tempt,matrix);
  clone(matrix,tempt);
}
