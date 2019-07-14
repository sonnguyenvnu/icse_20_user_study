private void computeOffset(int x0,int y0,int x1,int y1,int[] m){
  long lx=(long)x1 - (long)x0;
  long ly=(long)y1 - (long)y0;
  int dx, dy;
  if (m00 > 0 && m00 == m11 && m01 == 0 & m10 == 0) {
    long ilen=LinePath.hypot(lx,ly);
    if (ilen == 0) {
      dx=dy=0;
    }
 else {
      dx=(int)((ly * scaledLineWidth2) / ilen);
      dy=(int)(-(lx * scaledLineWidth2) / ilen);
    }
  }
 else {
    double dlx=x1 - x0;
    double dly=y1 - y0;
    double det=(double)m00 * m11 - (double)m01 * m10;
    int sdet=(det > 0) ? 1 : -1;
    double a=dly * m00 - dlx * m10;
    double b=dly * m01 - dlx * m11;
    double dh=LinePath.hypot(a,b);
    double div=sdet * lineWidth2 / (65536.0 * dh);
    double ddx=dly * m00_2_m01_2 - dlx * m00_m10_m01_m11;
    double ddy=dly * m00_m10_m01_m11 - dlx * m10_2_m11_2;
    dx=(int)(ddx * div);
    dy=(int)(ddy * div);
  }
  m[0]=dx;
  m[1]=dy;
}
