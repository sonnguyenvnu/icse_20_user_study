private void doOscEsc(int b){
switch (b) {
case '\\':
    doOscSetTextParameters("\033\\");
  break;
default :
collectOSCArgs(27);
collectOSCArgs(b);
continueSequence(ESC_OSC);
break;
}
}
