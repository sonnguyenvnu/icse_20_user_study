private void doOsc(int b){
switch (b) {
case 7:
    doOscSetTextParameters("\007");
  break;
case 27:
continueSequence(ESC_OSC_ESC);
break;
default :
collectOSCArgs(b);
break;
}
}
