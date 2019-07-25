/** 
 * Reverses all bits in the array.
 */
public void reverse(){
  int[] newBits=new int[bits.length];
  int len=(size - 1) / 32;
  int oldBitsLen=len + 1;
  for (int i=0; i < oldBitsLen; i++) {
    long x=bits[i];
    x=((x >> 1) & 0x55555555L) | ((x & 0x55555555L) << 1);
    x=((x >> 2) & 0x33333333L) | ((x & 0x33333333L) << 2);
    x=((x >> 4) & 0x0f0f0f0fL) | ((x & 0x0f0f0f0fL) << 4);
    x=((x >> 8) & 0x00ff00ffL) | ((x & 0x00ff00ffL) << 8);
    x=((x >> 16) & 0x0000ffffL) | ((x & 0x0000ffffL) << 16);
    newBits[len - i]=(int)x;
  }
  if (size != oldBitsLen * 32) {
    int leftOffset=oldBitsLen * 32 - size;
    int currentInt=newBits[0] >>> leftOffset;
    for (int i=1; i < oldBitsLen; i++) {
      int nextInt=newBits[i];
      currentInt|=nextInt << (32 - leftOffset);
      newBits[i - 1]=currentInt;
      currentInt=nextInt >>> leftOffset;
    }
    newBits[oldBitsLen - 1]=currentInt;
  }
  bits=newBits;
}
