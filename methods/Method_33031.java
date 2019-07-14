public ObjectProperty<BiFunction<JFXChipView<T>,T,JFXChip<T>>> chipFactoryProperty(){
  if (chipFactory == null) {
    chipFactory=new SimpleObjectProperty<>(this,"chipFactory");
  }
  return chipFactory;
}
