boolean isProgrammaticView(){
  return isStyleable() || layoutParams != Size.NONE;
}
