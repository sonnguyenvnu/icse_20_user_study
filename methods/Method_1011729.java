@Override public boolean equals(Object object){
  return super.equals(object) && this.myProperty.equals(((PropertyFeature)object).myProperty);
}
