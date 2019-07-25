public void scalexi(int X,int Y,int Z){
  double x=(X / 65536.0);
  double y=(Y / 65536.0);
  double z=(Z / 65536.0);
  temps[0]=x;
  temps[1]=0;
  temps[2]=0;
  temps[3]=0;
  temps[4]=0;
  temps[5]=y;
  temps[6]=0;
  temps[7]=0;
  temps[8]=0;
  temps[9]=0;
  temps[10]=z;
  temps[11]=0;
  temps[12]=0;
  temps[13]=0;
  temps[14]=0;
  temps[15]=1;
  matmul(temps,matrix);
  clone(matrix,temps);
}
