/** 
 * Reads a Synchsafe integer. <p> Synchsafe integers keep the highest bit of every byte zeroed. A 32 bit synchsafe integer can store 28 bits of information.
 * @return The parsed value.
 */
public int readSynchSafeInt(){
  int b1=readUnsignedByte();
  int b2=readUnsignedByte();
  int b3=readUnsignedByte();
  int b4=readUnsignedByte();
  return (b1 << 21) | (b2 << 14) | (b3 << 7) | b4;
}
