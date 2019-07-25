void execute(){
  int input=0;
  if (pins[7].value)   input+=8;
  if (pins[8].value)   input+=4;
  if (pins[9].value)   input+=2;
  if (pins[10].value)   input+=1;
  for (int i=0; i < 7; i++) {
    pins[i].value=symbols[input][i];
  }
}
