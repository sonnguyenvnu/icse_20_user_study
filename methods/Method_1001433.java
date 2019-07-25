public static ST_pointf[] malloc(int nb){
  final ST_pointf result[]=new ST_pointf[nb];
  for (int i=0; i < nb; i++) {
    result[i]=new ST_pointf();
  }
  return result;
}
