private UploadedMedia uploadMediaChunkedStatus(long mediaId) throws TwitterException {
  JSONObject json=get(conf.getUploadBaseURL() + "media/upload.json",new HttpParameter[]{new HttpParameter("command",CHUNKED_STATUS),new HttpParameter("media_id",mediaId)}).asJSONObject();
  logger.debug("Status response:" + json);
  return new UploadedMedia(json);
}
