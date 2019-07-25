public static ST_boxf[] malloc(int nb){
  final ST_boxf result[]=new ST_boxf[nb];
  for (int i=0; i < nb; i++) {
    result[i]=new ST_boxf();
  }
  return result;
}
