private static void readMappings(int channels,VorbisBitArray bitArray) throws ParserException {
  int mappingsCount=bitArray.readBits(6) + 1;
  for (int i=0; i < mappingsCount; i++) {
    int mappingType=bitArray.readBits(16);
switch (mappingType) {
case 0:
      int submaps;
    if (bitArray.readBit()) {
      submaps=bitArray.readBits(4) + 1;
    }
 else {
      submaps=1;
    }
  int couplingSteps;
if (bitArray.readBit()) {
  couplingSteps=bitArray.readBits(8) + 1;
  for (int j=0; j < couplingSteps; j++) {
    bitArray.skipBits(iLog(channels - 1));
    bitArray.skipBits(iLog(channels - 1));
  }
}
if (bitArray.readBits(2) != 0x00) {
throw new ParserException("to reserved bits must be zero after mapping coupling steps");
}
if (submaps > 1) {
for (int j=0; j < channels; j++) {
bitArray.skipBits(4);
}
}
for (int j=0; j < submaps; j++) {
bitArray.skipBits(8);
bitArray.skipBits(8);
bitArray.skipBits(8);
}
break;
default :
Log.e(TAG,"mapping type other than 0 not supported: " + mappingType);
}
}
}
