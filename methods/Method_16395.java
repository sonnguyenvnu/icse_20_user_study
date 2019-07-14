@Override @Cacheable(key="'md5:'+#md5",condition="#md5!=null") public FileInfoEntity selectByMd5(String md5){
  if (null == md5) {
    return null;
  }
  return createQuery().where(FileInfoEntity.md5,md5).single();
}
