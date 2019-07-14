/** 
 * ???????
 */
private void shrink(){
  int nbase[]=new int[size + 65535];
  System.arraycopy(base,0,nbase,0,size);
  base=nbase;
  int ncheck[]=new int[size + 65535];
  System.arraycopy(check,0,ncheck,0,size);
  check=ncheck;
}
