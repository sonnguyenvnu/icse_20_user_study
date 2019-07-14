private void createImageProcessor(){
switch (selectedMode) {
case CLOUD_LABEL_DETECTION:
    imageProcessor=new CloudImageLabelingProcessor();
  break;
case CLOUD_LANDMARK_DETECTION:
imageProcessor=new CloudLandmarkRecognitionProcessor();
break;
case CLOUD_TEXT_DETECTION:
imageProcessor=new CloudTextRecognitionProcessor();
break;
case CLOUD_DOCUMENT_TEXT_DETECTION:
imageProcessor=new CloudDocumentTextRecognitionProcessor();
break;
default :
throw new IllegalStateException("Unknown selectedMode: " + selectedMode);
}
}
