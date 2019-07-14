private void parsePathArcto(float x1,float y1,float rx,float ry,float angle,boolean fa,boolean fs,float x2,float y2){
  if (x1 == x2 && y1 == y2)   return;
  if (rx == 0 || ry == 0) {
    parsePathLineto(x2,y2);
    return;
  }
  rx=PApplet.abs(rx);
  ry=PApplet.abs(ry);
  float phi=PApplet.radians(((angle % 360) + 360) % 360);
  float cosPhi=PApplet.cos(phi), sinPhi=PApplet.sin(phi);
  float x1r=(cosPhi * (x1 - x2) + sinPhi * (y1 - y2)) / 2;
  float y1r=(-sinPhi * (x1 - x2) + cosPhi * (y1 - y2)) / 2;
  float cxr, cyr;
{
    float A=(x1r * x1r) / (rx * rx) + (y1r * y1r) / (ry * ry);
    if (A > 1) {
      float sqrtA=PApplet.sqrt(A);
      rx*=sqrtA;
      cxr=0;
      ry*=sqrtA;
      cyr=0;
    }
 else {
      float k=((fa == fs) ? -1f : 1f) * PApplet.sqrt((rx * rx * ry * ry) / ((rx * rx * y1r * y1r) + (ry * ry * x1r * x1r)) - 1f);
      cxr=k * rx * y1r / ry;
      cyr=-k * ry * x1r / rx;
    }
  }
  float cx=cosPhi * cxr - sinPhi * cyr + (x1 + x2) / 2;
  float cy=sinPhi * cxr + cosPhi * cyr + (y1 + y2) / 2;
  float phi1, phiDelta;
{
    float sx=(x1r - cxr) / rx, sy=(y1r - cyr) / ry;
    float tx=(-x1r - cxr) / rx, ty=(-y1r - cyr) / ry;
    phi1=PApplet.atan2(sy,sx);
    phiDelta=(((PApplet.atan2(ty,tx) - phi1) % TWO_PI) + TWO_PI) % TWO_PI;
    if (!fs)     phiDelta-=TWO_PI;
  }
  int segmentCount=PApplet.ceil(PApplet.abs(phiDelta) / TWO_PI * 4);
  float inc=phiDelta / segmentCount;
  float a=PApplet.sin(inc) * (PApplet.sqrt(4 + 3 * PApplet.sq(PApplet.tan(inc / 2))) - 1) / 3;
  float sinPhi1=PApplet.sin(phi1), cosPhi1=PApplet.cos(phi1);
  float p1x=x1;
  float p1y=y1;
  float relq1x=a * (-rx * cosPhi * sinPhi1 - ry * sinPhi * cosPhi1);
  float relq1y=a * (-rx * sinPhi * sinPhi1 + ry * cosPhi * cosPhi1);
  for (int i=0; i < segmentCount; i++) {
    float eta=phi1 + (i + 1) * inc;
    float sinEta=PApplet.sin(eta), cosEta=PApplet.cos(eta);
    float p2x=cx + rx * cosPhi * cosEta - ry * sinPhi * sinEta;
    float p2y=cy + rx * sinPhi * cosEta + ry * cosPhi * sinEta;
    float relq2x=a * (-rx * cosPhi * sinEta - ry * sinPhi * cosEta);
    float relq2y=a * (-rx * sinPhi * sinEta + ry * cosPhi * cosEta);
    if (i == segmentCount - 1) {
      p2x=x2;
      p2y=y2;
    }
    parsePathCode(BEZIER_VERTEX);
    parsePathVertex(p1x + relq1x,p1y + relq1y);
    parsePathVertex(p2x - relq2x,p2y - relq2y);
    parsePathVertex(p2x,p2y);
    p1x=p2x;
    relq1x=relq2x;
    p1y=p2y;
    relq1y=relq2y;
  }
}
