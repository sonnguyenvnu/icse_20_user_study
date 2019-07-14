public MediaDetailsMap<String,String> getMainDetails(Context context,Media m){
  MediaDetailsMap<String,String> details=new MediaDetailsMap<>();
  details.put(context.getString(R.string.path),m.getDisplayPath());
  details.put(context.getString(R.string.type),m.getMimeType());
  if (m.getSize() != -1)   details.put(context.getString(R.string.size),StringUtils.humanReadableByteCount(m.getSize(),true));
  details.put(context.getString(R.string.orientation),m.getOrientation() + "");
  MetaDataItem metadata=MetaDataItem.getMetadata(context,m.getUri());
  details.put(context.getString(R.string.resolution),metadata.getResolution());
  details.put(context.getString(R.string.date),SimpleDateFormat.getDateTimeInstance().format(new Date(m.getDateModified())));
  Date dateOriginal=metadata.getDateOriginal();
  if (dateOriginal != null)   details.put(context.getString(R.string.date_taken),SimpleDateFormat.getDateTimeInstance().format(dateOriginal));
  String tmp;
  if ((tmp=metadata.getCameraInfo()) != null)   details.put(context.getString(R.string.camera),tmp);
  if ((tmp=metadata.getExifInfo()) != null)   details.put(context.getString(R.string.exif),tmp);
  GeoLocation location;
  if ((location=metadata.getLocation()) != null)   details.put(context.getString(R.string.location),location.toDMSString());
  return details;
}
