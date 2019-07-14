static public boolean isRetina(){
  if (retinaProp == null) {
    retinaProp=checkRetina();
  }
  return retinaProp;
}
