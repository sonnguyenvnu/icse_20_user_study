/** 
 * Updates the job. <p> This just updates the job, but it doesn't schedule it. In order to be executed, the job has to be scheduled after being set. In case there was a previous job scheduled that has not yet started, this new job will be executed instead.
 * @return whether the job was successfully updated.
 */
public boolean updateJob(EncodedImage encodedImage,@Consumer.Status int status){
  if (!shouldProcess(encodedImage,status)) {
    return false;
  }
  EncodedImage oldEncodedImage;
synchronized (this) {
    oldEncodedImage=mEncodedImage;
    mEncodedImage=EncodedImage.cloneOrNull(encodedImage);
    mStatus=status;
  }
  EncodedImage.closeSafely(oldEncodedImage);
  return true;
}
