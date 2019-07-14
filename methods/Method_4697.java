/** 
 * Returns the duration of the given audio packet.
 * @param packet Contains audio data.
 * @return Returns the duration of the given audio packet.
 */
private long getPacketDurationUs(byte[] packet){
  int toc=packet[0] & 0xFF;
  int frames;
switch (toc & 0x3) {
case 0:
    frames=1;
  break;
case 1:
case 2:
frames=2;
break;
default :
frames=packet[1] & 0x3F;
break;
}
int config=toc >> 3;
int length=config & 0x3;
if (config >= 16) {
length=2500 << length;
}
 else if (config >= 12) {
length=10000 << (length & 0x1);
}
 else if (length == 3) {
length=60000;
}
 else {
length=10000 << length;
}
return (long)frames * length;
}
