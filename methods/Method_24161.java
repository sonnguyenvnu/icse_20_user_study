@Override protected void fillFromCalc(){
  super.fillFromCalc();
  if (!setAmbient) {
    ambientFromCalc();
    setAmbient=false;
  }
}
