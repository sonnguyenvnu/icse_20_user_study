private Map.Entry<T,Boolean> comparePoints(T one,boolean oneIncl,T two,boolean twoIncl,boolean chooseBigger){
  if (one == null)   return new AbstractMap.SimpleImmutableEntry<>(two,twoIncl);
  if (two == null)   return new AbstractMap.SimpleImmutableEntry<>(one,oneIncl);
  int c=((Comparable)one).compareTo(two);
  if (c == 0) {
    return new AbstractMap.SimpleImmutableEntry<>(one,oneIncl & twoIncl);
  }
 else   if ((c > 0 && chooseBigger) || (c < 0 && !chooseBigger)) {
    return new AbstractMap.SimpleImmutableEntry<>(one,oneIncl);
  }
 else {
    return new AbstractMap.SimpleImmutableEntry<>(two,twoIncl);
  }
}
