private void uploadMediaChunkedAppend(String fileName,InputStream media,int segmentIndex,long mediaId) throws TwitterException {
  post(conf.getUploadBaseURL() + "media/upload.json",new HttpParameter[]{new HttpParameter("command",CHUNKED_APPEND),new HttpParameter("media_id",mediaId),new HttpParameter("segment_index",segmentIndex),new HttpParameter("media",fileName,media)});
}
