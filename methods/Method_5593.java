@Override protected TtmlSubtitle decode(byte[] bytes,int length,boolean reset) throws SubtitleDecoderException {
  try {
    XmlPullParser xmlParser=xmlParserFactory.newPullParser();
    Map<String,TtmlStyle> globalStyles=new HashMap<>();
    Map<String,TtmlRegion> regionMap=new HashMap<>();
    Map<String,String> imageMap=new HashMap<>();
    regionMap.put(TtmlNode.ANONYMOUS_REGION_ID,new TtmlRegion(null));
    ByteArrayInputStream inputStream=new ByteArrayInputStream(bytes,0,length);
    xmlParser.setInput(inputStream,null);
    TtmlSubtitle ttmlSubtitle=null;
    ArrayDeque<TtmlNode> nodeStack=new ArrayDeque<>();
    int unsupportedNodeDepth=0;
    int eventType=xmlParser.getEventType();
    FrameAndTickRate frameAndTickRate=DEFAULT_FRAME_AND_TICK_RATE;
    CellResolution cellResolution=DEFAULT_CELL_RESOLUTION;
    TtsExtent ttsExtent=null;
    while (eventType != XmlPullParser.END_DOCUMENT) {
      TtmlNode parent=nodeStack.peek();
      if (unsupportedNodeDepth == 0) {
        String name=xmlParser.getName();
        if (eventType == XmlPullParser.START_TAG) {
          if (TtmlNode.TAG_TT.equals(name)) {
            frameAndTickRate=parseFrameAndTickRates(xmlParser);
            cellResolution=parseCellResolution(xmlParser,DEFAULT_CELL_RESOLUTION);
            ttsExtent=parseTtsExtent(xmlParser);
          }
          if (!isSupportedTag(name)) {
            Log.i(TAG,"Ignoring unsupported tag: " + xmlParser.getName());
            unsupportedNodeDepth++;
          }
 else           if (TtmlNode.TAG_HEAD.equals(name)) {
            parseHeader(xmlParser,globalStyles,cellResolution,ttsExtent,regionMap,imageMap);
          }
 else {
            try {
              TtmlNode node=parseNode(xmlParser,parent,regionMap,frameAndTickRate);
              nodeStack.push(node);
              if (parent != null) {
                parent.addChild(node);
              }
            }
 catch (            SubtitleDecoderException e) {
              Log.w(TAG,"Suppressing parser error",e);
              unsupportedNodeDepth++;
            }
          }
        }
 else         if (eventType == XmlPullParser.TEXT) {
          parent.addChild(TtmlNode.buildTextNode(xmlParser.getText()));
        }
 else         if (eventType == XmlPullParser.END_TAG) {
          if (xmlParser.getName().equals(TtmlNode.TAG_TT)) {
            ttmlSubtitle=new TtmlSubtitle(nodeStack.peek(),globalStyles,regionMap,imageMap);
          }
          nodeStack.pop();
        }
      }
 else {
        if (eventType == XmlPullParser.START_TAG) {
          unsupportedNodeDepth++;
        }
 else         if (eventType == XmlPullParser.END_TAG) {
          unsupportedNodeDepth--;
        }
      }
      xmlParser.next();
      eventType=xmlParser.getEventType();
    }
    return ttmlSubtitle;
  }
 catch (  XmlPullParserException xppe) {
    throw new SubtitleDecoderException("Unable to decode source",xppe);
  }
catch (  IOException e) {
    throw new IllegalStateException("Unexpected error when reading input.",e);
  }
}
