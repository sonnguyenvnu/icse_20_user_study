void execute(){
  int imax=(1 << bits) - 1;
  double val=imax * volts[bits] / volts[bits + 1];
  int ival=(int)val;
  ival=min(imax,max(0,ival));
  int i;
  for (i=0; i != bits; i++)   pins[i].value=((ival & (1 << i)) != 0);
}
