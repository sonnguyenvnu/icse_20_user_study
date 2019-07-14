private Color HSL2RGB(double hue,double sat,double lum){
  hue=map(hue,0,255,0,359);
  sat=map(sat,0,255,0,1);
  lum=map(lum,0,255,0,1);
  double v;
  double red, green, blue;
  double m;
  double sv;
  int sextant;
  double fract, vsf, mid1, mid2;
  red=lum;
  green=lum;
  blue=lum;
  v=(lum <= 0.5) ? (lum * (1.0 + sat)) : (lum + sat - lum * sat);
  m=lum + lum - v;
  sv=(v - m) / v;
  hue/=60.0;
  sextant=(int)Math.floor(hue);
  fract=hue - sextant;
  vsf=v * sv * fract;
  mid1=m + vsf;
  mid2=v - vsf;
  if (v > 0) {
switch (sextant) {
case 0:
      red=v;
    green=mid1;
  blue=m;
break;
case 1:
red=mid2;
green=v;
blue=m;
break;
case 2:
red=m;
green=v;
blue=mid1;
break;
case 3:
red=m;
green=mid2;
blue=v;
break;
case 4:
red=mid1;
green=m;
blue=v;
break;
case 5:
red=v;
green=m;
blue=mid2;
break;
}
}
return new Color(red,green,blue,1);
}
