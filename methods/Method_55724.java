protected static Member __array(int size,int alignment,int length){
  return new Member(size * length,alignment,false);
}
