private void setImages(){
  if (animationInProgress == 0) {
    setIndexToImage(centerImage,currentIndex);
    setIndexToImage(rightImage,currentIndex + 1);
    setIndexToImage(leftImage,currentIndex - 1);
  }
}
