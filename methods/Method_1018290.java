private void reset(){
  isWill=false;
  isDo=false;
  isWont=false;
  isDont=false;
  isSb=false;
  isHandlingCommand=false;
  telnetCommand=StringUtils.EMPTY_STRING;
  isEsc=false;
  escCommand=StringUtils.EMPTY_STRING;
}
