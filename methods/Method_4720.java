private static void readFloors(VorbisBitArray bitArray) throws ParserException {
  int floorCount=bitArray.readBits(6) + 1;
  for (int i=0; i < floorCount; i++) {
    int floorType=bitArray.readBits(16);
switch (floorType) {
case 0:
      bitArray.skipBits(8);
    bitArray.skipBits(16);
  bitArray.skipBits(16);
bitArray.skipBits(6);
bitArray.skipBits(8);
int floorNumberOfBooks=bitArray.readBits(4) + 1;
for (int j=0; j < floorNumberOfBooks; j++) {
bitArray.skipBits(8);
}
break;
case 1:
int partitions=bitArray.readBits(5);
int maximumClass=-1;
int[] partitionClassList=new int[partitions];
for (int j=0; j < partitions; j++) {
partitionClassList[j]=bitArray.readBits(4);
if (partitionClassList[j] > maximumClass) {
maximumClass=partitionClassList[j];
}
}
int[] classDimensions=new int[maximumClass + 1];
for (int j=0; j < classDimensions.length; j++) {
classDimensions[j]=bitArray.readBits(3) + 1;
int classSubclasses=bitArray.readBits(2);
if (classSubclasses > 0) {
bitArray.skipBits(8);
}
for (int k=0; k < (1 << classSubclasses); k++) {
bitArray.skipBits(8);
}
}
bitArray.skipBits(2);
int rangeBits=bitArray.readBits(4);
int count=0;
for (int j=0, k=0; j < partitions; j++) {
int idx=partitionClassList[j];
count+=classDimensions[idx];
for (; k < count; k++) {
bitArray.skipBits(rangeBits);
}
}
break;
default :
throw new ParserException("floor type greater than 1 not decodable: " + floorType);
}
}
}
