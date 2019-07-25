void execute(){
  int i;
  if (justLoaded) {
    justLoaded=false;
    return;
  }
  if (pins[0].value && !lastClock) {
    for (i=0; i != bits; i++)     if (pins[i + 2].value)     break;
    if (i < bits)     pins[i++ + 2].value=false;
    i%=bits;
    pins[i + 2].value=true;
  }
  if (!pins[1].value) {
    for (i=1; i != bits; i++)     pins[i + 2].value=false;
    pins[2].value=true;
  }
  lastClock=pins[0].value;
}
