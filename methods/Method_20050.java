@Override protected void onSuccess(@Nullable Bitmap originalCameraImage,@NonNull List<FirebaseVisionBarcode> barcodes,@NonNull FrameMetadata frameMetadata,@NonNull GraphicOverlay graphicOverlay){
  graphicOverlay.clear();
  if (originalCameraImage != null) {
    CameraImageGraphic imageGraphic=new CameraImageGraphic(graphicOverlay,originalCameraImage);
    graphicOverlay.add(imageGraphic);
  }
  for (int i=0; i < barcodes.size(); ++i) {
    FirebaseVisionBarcode barcode=barcodes.get(i);
    BarcodeGraphic barcodeGraphic=new BarcodeGraphic(graphicOverlay,barcode);
    graphicOverlay.add(barcodeGraphic);
  }
  graphicOverlay.postInvalidate();
}
