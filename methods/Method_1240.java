@Override public void onUltimateProducerReached(String requestId,String ultimateProducerName,boolean successful){
  if (mImageOriginLister != null) {
    mImageOriginLister.onImageLoaded(mControllerId,ImageOriginUtils.mapProducerNameToImageOrigin(ultimateProducerName),successful,ultimateProducerName);
  }
}
