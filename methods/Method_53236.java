HttpParameter[] asHttpParameterArray(){
  ArrayList<HttpParameter> params=new ArrayList<HttpParameter>();
  appendParameter("status",status,params);
  if (-1 != inReplyToStatusId) {
    appendParameter("in_reply_to_status_id",inReplyToStatusId,params);
  }
  if (location != null) {
    appendParameter("lat",location.getLatitude(),params);
    appendParameter("long",location.getLongitude(),params);
  }
  appendParameter("place_id",placeId,params);
  if (!displayCoordinates) {
    appendParameter("display_coordinates","false",params);
  }
  if (null != mediaFile) {
    params.add(new HttpParameter("media[]",mediaFile));
    params.add(new HttpParameter("possibly_sensitive",possiblySensitive));
  }
 else   if (mediaName != null && mediaBody != null) {
    params.add(new HttpParameter("media[]",mediaName,mediaBody));
    params.add(new HttpParameter("possibly_sensitive",possiblySensitive));
  }
 else   if (mediaIds != null && mediaIds.length >= 1) {
    params.add(new HttpParameter("media_ids",StringUtil.join(mediaIds)));
  }
  if (autoPopulateReplyMetadata) {
    appendParameter("auto_populate_reply_metadata","true",params);
  }
  appendParameter("attachment_url",attachmentUrl,params);
  HttpParameter[] paramArray=new HttpParameter[params.size()];
  return params.toArray(paramArray);
}
