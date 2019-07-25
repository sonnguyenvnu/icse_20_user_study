@Override public int save(Picture picture){
  return pictureDao.insertPicture(picture);
}
