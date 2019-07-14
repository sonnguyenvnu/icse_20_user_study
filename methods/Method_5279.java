/** 
 * Parses the number of channels from the value attribute of an AudioElementConfiguration with schemeIdUri "tag:dolby.com,2014:dash:audio_channel_configuration:2011", as defined by table E.5 in ETSI TS 102 366.
 * @param xpp The parser from which to read.
 * @return The parsed number of channels, or {@link Format#NO_VALUE} if the channel count couldnot be parsed.
 */
protected static int parseDolbyChannelConfiguration(XmlPullParser xpp){
  String value=Util.toLowerInvariant(xpp.getAttributeValue(null,"value"));
  if (value == null) {
    return Format.NO_VALUE;
  }
switch (value) {
case "4000":
    return 1;
case "a000":
  return 2;
case "f801":
return 6;
case "fa01":
return 8;
default :
return Format.NO_VALUE;
}
}
