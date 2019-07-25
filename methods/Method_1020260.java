public final void get(int[] a,int offset){
  if (a == null) {
    throw new NullPointerException();
  }
 else   if (offset < 0 || a.length - offset < 12) {
    throw new IllegalArgumentException();
  }
 else {
    a[offset]=this.m00;
    a[offset + 1]=this.m01;
    a[offset + 2]=this.m02;
    a[offset + 3]=this.m03;
    a[offset + 4]=this.m10;
    a[offset + 5]=this.m11;
    a[offset + 6]=this.m12;
    a[offset + 7]=this.m13;
    a[offset + 8]=this.m20;
    a[offset + 9]=this.m21;
    a[offset + 10]=this.m22;
    a[offset + 11]=this.m23;
  }
}
