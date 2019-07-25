protected DocValueFormat formatter(){
  if (format != null) {
    return new DocValueFormat.Decimal(format);
  }
 else {
    return DocValueFormat.RAW;
  }
}
