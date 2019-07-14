@Override public void process(final ByteBuffer data,final FrameMetadata frameMetadata,final GraphicOverlay graphicOverlay) throws FirebaseMLException {
  final Activity activity=activityRef.get();
  if (activity == null) {
    return;
  }
  classifier.classifyFrame(data,frameMetadata.getWidth(),frameMetadata.getHeight()).addOnSuccessListener(activity,new OnSuccessListener<List<String>>(){
    @Override public void onSuccess(    List<String> result){
      LabelGraphic labelGraphic=new LabelGraphic(graphicOverlay,result);
      Bitmap bitmap=BitmapUtils.getBitmap(data,frameMetadata);
      CameraImageGraphic imageGraphic=new CameraImageGraphic(graphicOverlay,bitmap);
      graphicOverlay.clear();
      graphicOverlay.add(imageGraphic);
      graphicOverlay.add(labelGraphic);
      graphicOverlay.postInvalidate();
    }
  }
).addOnFailureListener(new OnFailureListener(){
    @Override public void onFailure(    @NonNull Exception e){
      Log.d(TAG,"Custom classifier failed: " + e);
      e.printStackTrace();
    }
  }
);
}
