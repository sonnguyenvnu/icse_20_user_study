/** 
 * ????media
 */
@Override public void getMediaList(String bucketId,int pageSize,int currentOffset){
  mediaSrcFactoryInteractor.generateMeidas(bucketId,pageSize,currentOffset);
}
