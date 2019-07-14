private void createCameraSource(String model){
  if (cameraSource == null) {
    cameraSource=new CameraSource(this,graphicOverlay);
  }
  try {
switch (model) {
case CLASSIFICATION_QUANT:
      Log.i(TAG,"Using Custom Image Classifier (quant) Processor");
    cameraSource.setMachineLearningFrameProcessor(new CustomImageClassifierProcessor(this,true));
  break;
case CLASSIFICATION_FLOAT:
Log.i(TAG,"Using Custom Image Classifier (float) Processor");
cameraSource.setMachineLearningFrameProcessor(new CustomImageClassifierProcessor(this,false));
break;
case TEXT_DETECTION:
Log.i(TAG,"Using Text Detector Processor");
cameraSource.setMachineLearningFrameProcessor(new TextRecognitionProcessor());
break;
case FACE_DETECTION:
Log.i(TAG,"Using Face Detector Processor");
cameraSource.setMachineLearningFrameProcessor(new FaceDetectionProcessor(getResources()));
break;
case AUTOML_IMAGE_LABELING:
cameraSource.setMachineLearningFrameProcessor(new AutoMLImageLabelerProcessor(this));
break;
case OBJECT_DETECTION:
Log.i(TAG,"Using Object Detector Processor");
FirebaseVisionObjectDetectorOptions objectDetectorOptions=new FirebaseVisionObjectDetectorOptions.Builder().setDetectorMode(FirebaseVisionObjectDetectorOptions.STREAM_MODE).enableClassification().build();
cameraSource.setMachineLearningFrameProcessor(new ObjectDetectorProcessor(objectDetectorOptions));
break;
case BARCODE_DETECTION:
Log.i(TAG,"Using Barcode Detector Processor");
cameraSource.setMachineLearningFrameProcessor(new BarcodeScanningProcessor());
break;
case IMAGE_LABEL_DETECTION:
Log.i(TAG,"Using Image Label Detector Processor");
cameraSource.setMachineLearningFrameProcessor(new ImageLabelingProcessor());
break;
case FACE_CONTOUR:
Log.i(TAG,"Using Face Contour Detector Processor");
cameraSource.setMachineLearningFrameProcessor(new FaceContourDetectorProcessor());
break;
default :
Log.e(TAG,"Unknown model: " + model);
}
}
 catch (Exception e) {
Log.e(TAG,"Can not create image processor: " + model,e);
Toast.makeText(getApplicationContext(),"Can not create image processor: " + e.getMessage(),Toast.LENGTH_LONG).show();
}
}
