protected void emitTag(){
  tag.end(ndx + 1);
  if (config.calculatePosition) {
    tag.setPosition(position(tag.getTagPosition()));
  }
  if (tag.getType().isStartingTag()) {
    if (matchTagName(T_SCRIPT)) {
      scriptStartNdx=ndx + 1;
      state=SCRIPT_DATA;
      return;
    }
    if (config.enableRawTextModes) {
      for (      char[] rawtextTagName : RAWTEXT_TAGS) {
        if (matchTagName(rawtextTagName)) {
          tag.setRawTag(true);
          state=RAWTEXT;
          rawTextStart=ndx + 1;
          rawTagName=rawtextTagName;
          break;
        }
      }
      for (      char[] rcdataTextTagName : RCDATA_TAGS) {
        if (matchTagName(rcdataTextTagName)) {
          state=RCDATA;
          rcdataTagStart=ndx + 1;
          rcdataTagName=rcdataTextTagName;
          break;
        }
      }
    }
    tag.increaseDeepLevel();
  }
  visitor.tag(tag);
  if (tag.getType().isEndingTag()) {
    tag.decreaseDeepLevel();
  }
}
