private CPDRenderer createRenderer(){
  if (format.equals(TEXT_FORMAT)) {
    return new SimpleRenderer();
  }
 else   if (format.equals(CSV_FORMAT)) {
    return new CSVRenderer();
  }
  return new XMLRenderer();
}
