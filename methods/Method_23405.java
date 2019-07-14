/** 
 * Optimized code for building the blur kernel. further optimized blur code (approx. 15% for radius=20) bigger speed gains for larger radii (~30%) added support for various image types (ALPHA, RGB, ARGB) [toxi 050728]
 */
protected void buildBlurKernel(float r){
  int radius=(int)(r * 3.5f);
  radius=(radius < 1) ? 1 : ((radius < 248) ? radius : 248);
  if (blurRadius != radius) {
    blurRadius=radius;
    blurKernelSize=1 + blurRadius << 1;
    blurKernel=new int[blurKernelSize];
    blurMult=new int[blurKernelSize][256];
    int bk, bki;
    int[] bm, bmi;
    for (int i=1, radiusi=radius - 1; i < radius; i++) {
      blurKernel[radius + i]=blurKernel[radiusi]=bki=radiusi * radiusi;
      bm=blurMult[radius + i];
      bmi=blurMult[radiusi--];
      for (int j=0; j < 256; j++)       bm[j]=bmi[j]=bki * j;
    }
    bk=blurKernel[radius]=radius * radius;
    bm=blurMult[radius];
    for (int j=0; j < 256; j++)     bm[j]=bk * j;
  }
}
