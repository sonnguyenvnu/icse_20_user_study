public MediaDetailsMap<String,String> getAllDetails(Context context,Media media){
  MediaDetailsMap<String,String> data=new MediaDetailsMap<String,String>();
  try {
    Metadata metadata=ImageMetadataReader.readMetadata(context.getContentResolver().openInputStream(media.getUri()));
    for (    Directory directory : metadata.getDirectories()) {
      for (      Tag tag : directory.getTags()) {
        data.put(tag.getTagName(),directory.getObject(tag.getTagType()) + "");
      }
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return data;
}
