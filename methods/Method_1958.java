public void setImageResource(int resourceId){
  if (resourceId == R.color.placeholder) {
  }
 else   if (resourceId == R.color.error) {
    mInstrumentation.onFailure();
  }
 else {
    throw new IllegalArgumentException("Unrecognized resourceId");
  }
  super.setImageResource(resourceId);
}
