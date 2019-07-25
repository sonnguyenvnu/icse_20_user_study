void execute(){
  if (pins[0].value && !lastClock) {
    int i;
    int value=0;
    int dir=1;
    if (hasUpDown() && pins[bits + 2].value)     dir=-1;
    int lastBit=2 + bits - 1;
    for (i=0; i != bits; i++)     if (pins[lastBit - i].value)     value|=1 << i;
    value+=dir;
    if (modulus != 0)     value=(value + modulus) % modulus;
    for (i=0; i != bits; i++)     pins[lastBit - i].value=(value & (1 << i)) != 0;
  }
  if (!pins[1].value == invertreset) {
    int i;
    for (i=0; i != bits; i++)     pins[i + 2].value=false;
  }
  lastClock=pins[0].value;
}
