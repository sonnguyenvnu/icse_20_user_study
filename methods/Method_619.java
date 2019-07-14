/** 
 * Adds the specified symbol to the symbol table and returns a reference to the unique symbol. If the symbol already exists, the previous symbol reference is returned instead, in order guarantee that symbol references remain unique.
 * @param buffer The buffer containing the new symbol.
 * @param offset The offset into the buffer of the new symbol.
 * @param len The length of the new symbol in the buffer.
 */
public String addSymbol(char[] buffer,int offset,int len,int hash){
  final int bucket=hash & indexMask;
  String symbol=symbols[bucket];
  if (symbol != null) {
    boolean eq=true;
    if (hash == symbol.hashCode() && len == symbol.length()) {
      for (int i=0; i < len; i++) {
        if (buffer[offset + i] != symbol.charAt(i)) {
          eq=false;
          break;
        }
      }
    }
 else {
      eq=false;
    }
    if (eq) {
      return symbol;
    }
 else {
      return new String(buffer,offset,len);
    }
  }
  symbol=new String(buffer,offset,len).intern();
  symbols[bucket]=symbol;
  return symbol;
}
