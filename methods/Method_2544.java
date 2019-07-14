public int[] copy(){
  int[] ret=new int[_units.size()];
  System.arraycopy(_units.getBuffer(),0,ret,0,_units.size());
  return ret;
}
