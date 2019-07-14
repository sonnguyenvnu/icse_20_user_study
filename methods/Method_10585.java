private void initDecode(){
  multiFormatReader=new MultiFormatReader();
  Hashtable<DecodeHintType,Object> hints=new Hashtable<DecodeHintType,Object>(2);
  Vector<BarcodeFormat> decodeFormats=new Vector<BarcodeFormat>();
  if (decodeFormats == null || decodeFormats.isEmpty()) {
    decodeFormats=new Vector<BarcodeFormat>();
    Vector<BarcodeFormat> PRODUCT_FORMATS=new Vector<BarcodeFormat>(5);
    PRODUCT_FORMATS.add(BarcodeFormat.UPC_A);
    PRODUCT_FORMATS.add(BarcodeFormat.UPC_E);
    PRODUCT_FORMATS.add(BarcodeFormat.EAN_13);
    PRODUCT_FORMATS.add(BarcodeFormat.EAN_8);
    Vector<BarcodeFormat> ONE_D_FORMATS=new Vector<BarcodeFormat>(PRODUCT_FORMATS.size() + 4);
    ONE_D_FORMATS.addAll(PRODUCT_FORMATS);
    ONE_D_FORMATS.add(BarcodeFormat.CODE_39);
    ONE_D_FORMATS.add(BarcodeFormat.CODE_93);
    ONE_D_FORMATS.add(BarcodeFormat.CODE_128);
    ONE_D_FORMATS.add(BarcodeFormat.ITF);
    Vector<BarcodeFormat> QR_CODE_FORMATS=new Vector<BarcodeFormat>(1);
    QR_CODE_FORMATS.add(BarcodeFormat.QR_CODE);
    Vector<BarcodeFormat> DATA_MATRIX_FORMATS=new Vector<BarcodeFormat>(1);
    DATA_MATRIX_FORMATS.add(BarcodeFormat.DATA_MATRIX);
    decodeFormats.addAll(ONE_D_FORMATS);
    decodeFormats.addAll(QR_CODE_FORMATS);
    decodeFormats.addAll(DATA_MATRIX_FORMATS);
  }
  hints.put(DecodeHintType.POSSIBLE_FORMATS,decodeFormats);
  multiFormatReader.setHints(hints);
}
