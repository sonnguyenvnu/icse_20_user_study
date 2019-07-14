public void print(){
  int big=(int)Math.abs(max(max(max(max(abs(m00),abs(m01)),max(abs(m02),abs(m03))),max(max(abs(m10),abs(m11)),max(abs(m12),abs(m13)))),max(max(max(abs(m20),abs(m21)),max(abs(m22),abs(m23))),max(max(abs(m30),abs(m31)),max(abs(m32),abs(m33))))));
  int digits=1;
  if (Float.isNaN(big) || Float.isInfinite(big)) {
    digits=5;
  }
 else {
    while ((big/=10) != 0)     digits++;
  }
  System.out.println(PApplet.nfs(m00,digits,4) + " " + PApplet.nfs(m01,digits,4) + " " + PApplet.nfs(m02,digits,4) + " " + PApplet.nfs(m03,digits,4));
  System.out.println(PApplet.nfs(m10,digits,4) + " " + PApplet.nfs(m11,digits,4) + " " + PApplet.nfs(m12,digits,4) + " " + PApplet.nfs(m13,digits,4));
  System.out.println(PApplet.nfs(m20,digits,4) + " " + PApplet.nfs(m21,digits,4) + " " + PApplet.nfs(m22,digits,4) + " " + PApplet.nfs(m23,digits,4));
  System.out.println(PApplet.nfs(m30,digits,4) + " " + PApplet.nfs(m31,digits,4) + " " + PApplet.nfs(m32,digits,4) + " " + PApplet.nfs(m33,digits,4));
  System.out.println();
}
