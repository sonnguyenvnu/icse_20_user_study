public Printer<?> getPrinter(MaskFormat annotation,Class<?> fieldType){
  return new MaskFormatter(annotation.value());
}
