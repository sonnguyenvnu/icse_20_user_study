protected int parseAudioChannelConfiguration(XmlPullParser xpp) throws XmlPullParserException, IOException {
  String schemeIdUri=parseString(xpp,"schemeIdUri",null);
  int audioChannels="urn:mpeg:dash:23003:3:audio_channel_configuration:2011".equals(schemeIdUri) ? parseInt(xpp,"value",Format.NO_VALUE) : ("tag:dolby.com,2014:dash:audio_channel_configuration:2011".equals(schemeIdUri) ? parseDolbyChannelConfiguration(xpp) : Format.NO_VALUE);
  do {
    xpp.next();
  }
 while (!XmlPullParserUtil.isEndTag(xpp,"AudioChannelConfiguration"));
  return audioChannels;
}
