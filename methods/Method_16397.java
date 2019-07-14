@Override public FileInfoEntity selectByIdOrMd5(String idOrMd5){
  return selectSingle(QueryParamEntity.single(FileInfoEntity.id,idOrMd5).or(FileInfoEntity.md5,idOrMd5));
}
