public void updateMatrix(){
  presentationMatrix.reset();
  presentationMatrix.postTranslate(-state.getWidth() / 2,-state.getHeight() / 2);
  presentationMatrix.postRotate(state.getOrientation());
  state.getConcatMatrix(presentationMatrix);
  presentationMatrix.postTranslate(areaView.getCropCenterX(),areaView.getCropCenterY());
  imageView.setImageMatrix(presentationMatrix);
}
