public static int size(Random rnd,MagicTable mt){
  int i=0;
  while (true) {
    final int candidate=mt.getRandomFree(rnd);
    if (candidate == -1) {
      break;
    }
    mt.burnNumber(candidate);
    i++;
  }
  return i;
}
