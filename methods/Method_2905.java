public Matrix block(int i,int j,int p,int q){
  return getMatrix(i,i + p - 1,j,j + q - 1);
}
