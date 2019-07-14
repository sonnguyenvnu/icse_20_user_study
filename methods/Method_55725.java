protected static Member __array(int size,int alignment,boolean forceAlignment,int length){
  return new Member(size * length,alignment,forceAlignment);
}
