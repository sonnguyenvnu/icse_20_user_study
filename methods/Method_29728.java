@Override public void receiveDetections(Detector.Detections<TextBlock> detections){
  SparseArray<TextBlock> detectedItems=detections.getDetectedItems();
  for (int i=0; i < detectedItems.size(); ++i) {
    TextBlock item=detectedItems.valueAt(i);
    if (item != null && item.getValue() != null) {
      CameraKitTextDetect event=new CameraKitTextDetect(new CameraKitTextBlock(item));
      mEventDispatcher.dispatch(event);
      callback.callback(event);
    }
  }
}
