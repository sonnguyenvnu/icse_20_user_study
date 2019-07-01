private int _XXXXX_(final int[] table,final int[] mins,final int[] maxs,final int precision){
  int sum=0;
  for (int blue=mins[2]; blue <= maxs[2]; blue++) {
    final int b=(blue << (2 * precision));
    for (int green=mins[1]; green <= maxs[1]; green++) {
      final int g=(green << (1 * precision));
      for (int red=mins[0]; red <= maxs[0]; red++) {
        final int index=b | g | red;
        sum+=table[index];
      }
    }
  }
  return sum;
}