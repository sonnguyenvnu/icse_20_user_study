private void doEscPound(int b){
switch (b) {
case '8':
    mScreen.blockSet(0,0,mColumns,mRows,'E',getStyle());
  break;
default :
unknownSequence(b);
break;
}
}
