void execute(){
  if (pins[0].value && !modestate) {
    modestate=true;
    data=0;
    if (pins[2].value)     data+=128;
    if (pins[3].value)     data+=64;
    if (pins[4].value)     data+=32;
    if (pins[5].value)     data+=16;
    if (pins[6].value)     data+=8;
    if (pins[7].value)     data+=4;
    if (pins[8].value)     data+=2;
    if (pins[9].value)     data+=1;
  }
 else   if (pins[1].value && !clockstate) {
    clockstate=true;
    if ((data & 1) == 0)     pins[10].value=false;
 else     pins[10].value=true;
    data=(byte)(data >>> 1);
  }
  if (!pins[0].value)   modestate=false;
  if (!pins[1].value)   clockstate=false;
}
