AttachedPicture parseAttachedPictureFrame(ID3v2FrameBody data) throws IOException, ID3v2Exception {
  ID3v2Encoding encoding=data.readEncoding();
  String imageType;
  if (data.getTagHeader().getVersion() == 2) {
    String fileType=data.readFixedLengthString(3,ID3v2Encoding.ISO_8859_1);
switch (fileType.toUpperCase()) {
case "PNG":
      imageType="image/png";
    break;
case "JPG":
  imageType="image/jpeg";
break;
default :
imageType="image/unknown";
}
}
 else {
imageType=data.readZeroTerminatedString(20,ID3v2Encoding.ISO_8859_1);
}
byte pictureType=data.getData().readByte();
String description=data.readZeroTerminatedString(200,encoding);
byte[] imageData=data.getData().readFully((int)data.getRemainingLength());
return new AttachedPicture(pictureType,description,imageType,imageData);
}
