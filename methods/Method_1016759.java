private int[] sort(int i,int j){
  int[] ret=new int[2];
  if (i < j) {
    ret[0]=i;
    ret[1]=j;
  }
 else {
    ret[0]=j;
    ret[1]=i;
  }
  return ret;
}
