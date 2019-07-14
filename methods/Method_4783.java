private void parseFrameLength(ParsableBitArray data){
  frameLengthType=data.readBits(3);
switch (frameLengthType) {
case 0:
    data.skipBits(8);
  break;
case 1:
data.skipBits(9);
break;
case 3:
case 4:
case 5:
data.skipBits(6);
break;
case 6:
case 7:
data.skipBits(1);
break;
default :
throw new IllegalStateException();
}
}
