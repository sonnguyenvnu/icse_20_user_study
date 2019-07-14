private void rotateImage(int rotationDegree){
  Fragment mediaFragment=adapter.getRegisteredFragment(position);
  if (!(mediaFragment instanceof ImageFragment))   throw new RuntimeException("Trying to rotate a wrong media type!");
  ((ImageFragment)mediaFragment).rotatePicture(rotationDegree);
}
