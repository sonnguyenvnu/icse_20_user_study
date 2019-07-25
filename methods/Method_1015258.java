public static boolean equals(OfInt a,OfInt b){
  while (a.hasNext()) {
    if (a.nextInt() != b.nextInt()) {
      return false;
    }
  }
  return true;
}
