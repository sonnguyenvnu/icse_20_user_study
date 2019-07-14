private UploadedMedia uploadMediaChunkedFinalize0(long mediaId) throws TwitterException {
  JSONObject json=post(conf.getUploadBaseURL() + "media/upload.json",new HttpParameter[]{new HttpParameter("command",CHUNKED_FINALIZE),new HttpParameter("media_id",mediaId)}).asJSONObject();
  logger.debug("Finalize response:" + json);
  return new UploadedMedia(json);
}
