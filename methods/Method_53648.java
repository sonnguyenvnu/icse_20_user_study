public double getValue(double time){
switch (mType) {
default :
case SIN_WAVE:
    return Math.sin(PI2 * getP(time));
case SQUARE_WAVE:
  return Math.signum(0.5 - getP(time) % 1);
case TRIANGLE_WAVE:
return 1 - Math.abs(((getP(time)) * 4 + 1) % 4 - 2);
case SAW_WAVE:
return ((getP(time) * 2 + 1) % 2) - 1;
case REVERSE_SAW_WAVE:
return (1 - ((getP(time) * 2 + 1) % 2));
}
}
