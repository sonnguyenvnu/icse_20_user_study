/** 
 * Writes a byte array at the index.
 * @param code	may be a zero-length array.
 */
public void write(byte[] code,int index){
  int len=code.length;
  for (int j=0; j < len; ++j)   bytecode[index++]=code[j];
}
