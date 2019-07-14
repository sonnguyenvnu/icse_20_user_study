public void copyMediaBean(MediaBean mediaBean){
  if (mediaBean != null) {
    setId(mediaBean.getId());
    setTitle(mediaBean.getTitle());
    setOriginalPath(mediaBean.getOriginalPath());
    setCreateDate(mediaBean.getCreateDate());
    setModifiedDate(mediaBean.getModifiedDate());
    setMimeType(mediaBean.getMimeType());
    setBucketId(mediaBean.getBucketId());
    setBucketDisplayName(mediaBean.getBucketDisplayName());
    setThumbnailSmallPath(mediaBean.getThumbnailSmallPath());
    setThumbnailBigPath(mediaBean.getThumbnailBigPath());
  }
}
