static Annotation create(final int value){
  return new Internal(){
    @Override public int value(){
      return value;
    }
    @Override public Class<? extends Annotation> annotationType(){
      return Internal.class;
    }
    @Override public String toString(){
      return "@" + Internal.class.getName() + "(value=" + value + ")";
    }
    @Override public boolean equals(    Object o){
      return o instanceof Internal && ((Internal)o).value() == value();
    }
    @Override public int hashCode(){
      return (127 * "value".hashCode()) ^ value;
    }
  }
;
}
