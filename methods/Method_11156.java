private void ensurePair(int fractionsLength,int valuesLength){
  if (fractionsLength != valuesLength) {
    throw new IllegalStateException(String.format(Locale.getDefault(),"The fractions.length must equal values.length, " + "fraction.length[%d], values.length[%d]",fractionsLength,valuesLength));
  }
}
