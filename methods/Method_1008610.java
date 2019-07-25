ExtendedBounds round(Rounding rounding){
  return new ExtendedBounds(min != null ? rounding.round(min) : null,max != null ? rounding.round(max) : null);
}
