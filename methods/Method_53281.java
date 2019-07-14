private UploadedMedia uploadMediaChunkedInit(long size) throws TwitterException {
  return new UploadedMedia(post(conf.getUploadBaseURL() + "media/upload.json",new HttpParameter[]{new HttpParameter("command",CHUNKED_INIT),new HttpParameter("media_type","video/mp4"),new HttpParameter("media_category","tweet_video"),new HttpParameter("total_bytes",size)}).asJSONObject());
}
