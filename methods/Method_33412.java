public final ObjectProperty<Color> fromValueProperty(){
  if (fromValue == null) {
    fromValue=new SimpleObjectProperty<>(this,"fromValue",DEFAULT_FROM_VALUE);
  }
  return fromValue;
}
