@Override @Cacheable(key="'id-or-md5:'+#idOrMd5",condition="#idOrMd5!=null") public FileInfoEntity selectByIdOrMd5(String idOrMd5){
  if (null == idOrMd5) {
    return null;
  }
  return createQuery().where(FileInfoEntity.md5,idOrMd5).or(FileInfoEntity.id,idOrMd5).single();
}
