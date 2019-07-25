/** 
 * Configurably corrupt memory
 * @param blockSize Block size (in words) in which to randomly flip one bit
 */
public void corrupt(int blockSize){
  Random rand=new Random();
  for (int offset=0; offset < words.length; offset+=blockSize) {
    int address=rand.nextInt(blockSize) + offset;
    if (address < words.length) {
      int bitmask=1 << rand.nextInt(16);
      words[address]^=bitmask;
    }
  }
}
