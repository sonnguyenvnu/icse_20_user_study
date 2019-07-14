static protected PMatrix2D parseSingleTransform(String matrixStr){
  String[] pieces=PApplet.match(matrixStr,"[,\\s]*(\\w+)\\((.*)\\)");
  if (pieces == null) {
    System.err.println("Could not parse transform " + matrixStr);
    return null;
  }
  float[] m=PApplet.parseFloat(PApplet.splitTokens(pieces[2],", "));
  if (pieces[1].equals("matrix")) {
    return new PMatrix2D(m[0],m[2],m[4],m[1],m[3],m[5]);
  }
 else   if (pieces[1].equals("translate")) {
    float tx=m[0];
    float ty=(m.length == 2) ? m[1] : m[0];
    return new PMatrix2D(1,0,tx,0,1,ty);
  }
 else   if (pieces[1].equals("scale")) {
    float sx=m[0];
    float sy=(m.length == 2) ? m[1] : m[0];
    return new PMatrix2D(sx,0,0,0,sy,0);
  }
 else   if (pieces[1].equals("rotate")) {
    float angle=m[0];
    if (m.length == 1) {
      float c=PApplet.cos(angle);
      float s=PApplet.sin(angle);
      return new PMatrix2D(c,-s,0,s,c,0);
    }
 else     if (m.length == 3) {
      PMatrix2D mat=new PMatrix2D(0,1,m[1],1,0,m[2]);
      mat.rotate(m[0]);
      mat.translate(-m[1],-m[2]);
      return mat;
    }
  }
 else   if (pieces[1].equals("skewX")) {
    return new PMatrix2D(1,0,1,PApplet.tan(m[0]),0,0);
  }
 else   if (pieces[1].equals("skewY")) {
    return new PMatrix2D(1,0,1,0,PApplet.tan(m[0]),0);
  }
  return null;
}
