public boolean load(ByteArray byteArray){
  m=byteArray.nextInt();
  n=byteArray.nextInt();
  A=new double[m][n];
  for (int i=0; i < m; i++) {
    for (int j=0; j < n; j++) {
      A[i][j]=byteArray.nextDouble();
    }
  }
  return true;
}
