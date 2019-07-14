protected void colorCalc(float x,float y,float z,float a){
  if (x > colorModeX)   x=colorModeX;
  if (y > colorModeY)   y=colorModeY;
  if (z > colorModeZ)   z=colorModeZ;
  if (a > colorModeA)   a=colorModeA;
  if (x < 0)   x=0;
  if (y < 0)   y=0;
  if (z < 0)   z=0;
  if (a < 0)   a=0;
switch (colorMode) {
case RGB:
    if (colorModeScale) {
      calcR=x / colorModeX;
      calcG=y / colorModeY;
      calcB=z / colorModeZ;
      calcA=a / colorModeA;
    }
 else {
      calcR=x;
      calcG=y;
      calcB=z;
      calcA=a;
    }
  break;
case HSB:
x/=colorModeX;
y/=colorModeY;
z/=colorModeZ;
calcA=colorModeScale ? (a / colorModeA) : a;
if (y == 0) {
calcR=calcG=calcB=z;
}
 else {
float which=(x - (int)x) * 6.0f;
float f=which - (int)which;
float p=z * (1.0f - y);
float q=z * (1.0f - y * f);
float t=z * (1.0f - (y * (1.0f - f)));
switch ((int)which) {
case 0:
calcR=z;
calcG=t;
calcB=p;
break;
case 1:
calcR=q;
calcG=z;
calcB=p;
break;
case 2:
calcR=p;
calcG=z;
calcB=t;
break;
case 3:
calcR=p;
calcG=q;
calcB=z;
break;
case 4:
calcR=t;
calcG=p;
calcB=z;
break;
case 5:
calcR=z;
calcG=p;
calcB=q;
break;
}
}
break;
}
calcRi=(int)(255 * calcR);
calcGi=(int)(255 * calcG);
calcBi=(int)(255 * calcB);
calcAi=(int)(255 * calcA);
calcColor=(calcAi << 24) | (calcRi << 16) | (calcGi << 8) | calcBi;
calcAlpha=(calcAi != 255);
}
