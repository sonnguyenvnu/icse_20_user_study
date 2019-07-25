void execute(){
  int val=0;
  int i;
  for (i=0; i != selectBitCount; i++)   if (pins[i + outputCount].value)   val|=1 << i;
  for (i=0; i != outputCount; i++)   pins[i].value=false;
  pins[val].value=pins[qPin].value;
}
