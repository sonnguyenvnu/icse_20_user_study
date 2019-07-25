void execute(){
  int selectedValue=0;
  int i;
  for (i=0; i != selectBitCount; i++)   if (pins[outputCount + i].value)   selectedValue|=1 << i;
  pins[outputCount + selectBitCount].value=pins[selectedValue].value;
}
