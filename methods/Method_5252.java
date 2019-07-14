protected int parseContentType(XmlPullParser xpp){
  String contentType=xpp.getAttributeValue(null,"contentType");
  return TextUtils.isEmpty(contentType) ? C.TRACK_TYPE_UNKNOWN : MimeTypes.BASE_TYPE_AUDIO.equals(contentType) ? C.TRACK_TYPE_AUDIO : MimeTypes.BASE_TYPE_VIDEO.equals(contentType) ? C.TRACK_TYPE_VIDEO : MimeTypes.BASE_TYPE_TEXT.equals(contentType) ? C.TRACK_TYPE_TEXT : C.TRACK_TYPE_UNKNOWN;
}
