void execute(){
  if (pins[1].value && !clockstate) {
    clockstate=true;
    data=(short)(data >>> 1);
    if (pins[0].value)     data+=128;
    if ((data & 128) > 0)     pins[2].value=true;
 else     pins[2].value=false;
    if ((data & 64) > 0)     pins[3].value=true;
 else     pins[3].value=false;
    if ((data & 32) > 0)     pins[4].value=true;
 else     pins[4].value=false;
    if ((data & 16) > 0)     pins[5].value=true;
 else     pins[5].value=false;
    if ((data & 8) > 0)     pins[6].value=true;
 else     pins[6].value=false;
    if ((data & 4) > 0)     pins[7].value=true;
 else     pins[7].value=false;
    if ((data & 2) > 0)     pins[8].value=true;
 else     pins[8].value=false;
    if ((data & 1) > 0)     pins[9].value=true;
 else     pins[9].value=false;
  }
  if (!pins[1].value)   clockstate=false;
}
