/** 
 * X?????????
 * @return
 */
int getXWeight(){
  int product=1;
  Cell c=this.getHead();
  while (c != null && c.getLexeme() != null) {
    product*=c.getLexeme().getLength();
    c=c.getNext();
  }
  return product;
}
