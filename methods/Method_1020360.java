/** 
 * Converts this (PositionList, LocalList) pair into a state machine sequence.
 * @return {@code non-null;} encoded byte sequence without padding andterminated with a  {@code 0x00} byte
 */
public byte[] convert(){
  try {
    byte[] ret;
    ret=convert0();
    if (DEBUG) {
      for (int i=0; i < ret.length; i++) {
        System.err.printf("byte %02x\n",(0xff & ret[i]));
      }
    }
    return ret;
  }
 catch (  IOException ex) {
    throw ExceptionWithContext.withContext(ex,"...while encoding debug info");
  }
}
