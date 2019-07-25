@Override public FileInfo query(String fileUrl){
  List<FileInfo> result=fileInfoDao.queryBuilder().where(FileInfoDao.Properties.Url.eq(fileUrl)).build().list();
  if (result.size() != 0) {
    return result.get(0);
  }
  return null;
}
