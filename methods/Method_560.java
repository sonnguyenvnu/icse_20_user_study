protected final void copyTo(int offset,int count,char[] dest){
  System.arraycopy(buf,offset,dest,0,count);
}
