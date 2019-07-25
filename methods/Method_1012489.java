void setup(DiodeModel model){
  leakage=model.saturationCurrent;
  zvoltage=model.breakdownVoltage;
  vscale=model.vscale;
  vdcoef=model.vdcoef;
  vcrit=vscale * Math.log(vscale / (Math.sqrt(2) * leakage));
  vzcrit=vt * Math.log(vt / (Math.sqrt(2) * leakage));
  if (zvoltage == 0)   zoffset=0;
 else {
    double i=-.005;
    zoffset=zvoltage - Math.log(-(1 + i / leakage)) / vzcoef;
  }
}
