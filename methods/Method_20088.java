private void detectInVisionImage(final Bitmap originalCameraImage,FirebaseVisionImage image,final FrameMetadata metadata,final GraphicOverlay graphicOverlay){
  detectInImage(image).addOnSuccessListener(new OnSuccessListener<T>(){
    @Override public void onSuccess(    T results){
      VisionProcessorBase.this.onSuccess(originalCameraImage,results,metadata,graphicOverlay);
      processLatestImage(graphicOverlay);
    }
  }
).addOnFailureListener(new OnFailureListener(){
    @Override public void onFailure(    @NonNull Exception e){
      VisionProcessorBase.this.onFailure(e);
    }
  }
);
}
