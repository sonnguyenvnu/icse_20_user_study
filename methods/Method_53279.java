@Override public UploadedMedia uploadMedia(File image) throws TwitterException {
  checkFileValidity(image);
  return new UploadedMedia(post(conf.getUploadBaseURL() + "media/upload.json",new HttpParameter("media",image)).asJSONObject());
}
