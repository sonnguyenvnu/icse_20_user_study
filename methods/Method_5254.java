/** 
 * Parses a ContentProtection element.
 * @param xpp The parser from which to read.
 * @throws XmlPullParserException If an error occurs parsing the element.
 * @throws IOException If an error occurs reading the element.
 * @return The scheme type and/or {@link SchemeData} parsed from the ContentProtection element.Either or both may be null, depending on the ContentProtection element being parsed.
 */
protected Pair<String,SchemeData> parseContentProtection(XmlPullParser xpp) throws XmlPullParserException, IOException {
  String schemeType=null;
  String licenseServerUrl=null;
  byte[] data=null;
  UUID uuid=null;
  boolean requiresSecureDecoder=false;
  String schemeIdUri=xpp.getAttributeValue(null,"schemeIdUri");
  if (schemeIdUri != null) {
switch (Util.toLowerInvariant(schemeIdUri)) {
case "urn:mpeg:dash:mp4protection:2011":
      schemeType=xpp.getAttributeValue(null,"value");
    String defaultKid=XmlPullParserUtil.getAttributeValueIgnorePrefix(xpp,"default_KID");
  if (!TextUtils.isEmpty(defaultKid) && !"00000000-0000-0000-0000-000000000000".equals(defaultKid)) {
    String[] defaultKidStrings=defaultKid.split("\\s+");
    UUID[] defaultKids=new UUID[defaultKidStrings.length];
    for (int i=0; i < defaultKidStrings.length; i++) {
      defaultKids[i]=UUID.fromString(defaultKidStrings[i]);
    }
    data=PsshAtomUtil.buildPsshAtom(C.COMMON_PSSH_UUID,defaultKids,null);
    uuid=C.COMMON_PSSH_UUID;
  }
break;
case "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95":
uuid=C.PLAYREADY_UUID;
break;
case "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed":
uuid=C.WIDEVINE_UUID;
break;
default :
break;
}
}
do {
xpp.next();
if (XmlPullParserUtil.isStartTag(xpp,"ms:laurl")) {
licenseServerUrl=xpp.getAttributeValue(null,"licenseUrl");
}
 else if (XmlPullParserUtil.isStartTag(xpp,"widevine:license")) {
String robustnessLevel=xpp.getAttributeValue(null,"robustness_level");
requiresSecureDecoder=robustnessLevel != null && robustnessLevel.startsWith("HW");
}
 else if (data == null && XmlPullParserUtil.isStartTagIgnorePrefix(xpp,"pssh") && xpp.next() == XmlPullParser.TEXT) {
data=Base64.decode(xpp.getText(),Base64.DEFAULT);
uuid=PsshAtomUtil.parseUuid(data);
if (uuid == null) {
Log.w(TAG,"Skipping malformed cenc:pssh data");
data=null;
}
}
 else if (data == null && C.PLAYREADY_UUID.equals(uuid) && XmlPullParserUtil.isStartTag(xpp,"mspr:pro") && xpp.next() == XmlPullParser.TEXT) {
data=PsshAtomUtil.buildPsshAtom(C.PLAYREADY_UUID,Base64.decode(xpp.getText(),Base64.DEFAULT));
}
 else {
maybeSkipTag(xpp);
}
}
 while (!XmlPullParserUtil.isEndTag(xpp,"ContentProtection"));
SchemeData schemeData=uuid != null ? new SchemeData(uuid,licenseServerUrl,MimeTypes.VIDEO_MP4,data,requiresSecureDecoder) : null;
return Pair.create(schemeType,schemeData);
}
