protected void tessellateRect(){
  float a=0, b=0, c=0, d=0;
  float tl=0, tr=0, br=0, bl=0;
  boolean rounded=false;
  int mode=rectMode;
  if (params.length == 4 || params.length == 5) {
    a=params[0];
    b=params[1];
    c=params[2];
    d=params[3];
    rounded=false;
    if (params.length == 5) {
      tl=params[4];
      tr=params[4];
      br=params[4];
      bl=params[4];
      rounded=true;
    }
  }
 else   if (params.length == 8) {
    a=params[0];
    b=params[1];
    c=params[2];
    d=params[3];
    tl=params[4];
    tr=params[5];
    br=params[6];
    bl=params[7];
    rounded=true;
  }
  float hradius, vradius;
switch (mode) {
case CORNERS:
    break;
case CORNER:
  c+=a;
d+=b;
break;
case RADIUS:
hradius=c;
vradius=d;
c=a + hradius;
d=b + vradius;
a-=hradius;
b-=vradius;
break;
case CENTER:
hradius=c / 2.0f;
vradius=d / 2.0f;
c=a + hradius;
d=b + vradius;
a-=hradius;
b-=vradius;
}
if (a > c) {
float temp=a;
a=c;
c=temp;
}
if (b > d) {
float temp=b;
b=d;
d=temp;
}
float maxRounding=PApplet.min((c - a) / 2,(d - b) / 2);
if (tl > maxRounding) tl=maxRounding;
if (tr > maxRounding) tr=maxRounding;
if (br > maxRounding) br=maxRounding;
if (bl > maxRounding) bl=maxRounding;
inGeo.setMaterial(fillColor,strokeColor,strokeWeight,ambientColor,specularColor,emissiveColor,shininess);
inGeo.setNormal(normalX,normalY,normalZ);
if (rounded) {
saveBezierVertexSettings();
inGeo.addRect(a,b,c,d,tl,tr,br,bl,stroke);
tessellator.tessellatePolygon(true,true,true);
restoreBezierVertexSettings();
}
 else {
inGeo.addRect(a,b,c,d,stroke);
tessellator.tessellateQuads();
}
}
