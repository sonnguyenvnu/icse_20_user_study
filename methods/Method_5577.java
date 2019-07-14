/** 
 * Build a  {@link Cue} based on the given text and alignment tag.
 * @param text The text.
 * @param alignmentTag The alignment tag, or {@code null} if no alignment tag is available.
 * @return Built cue
 */
private Cue buildCue(Spanned text,@Nullable String alignmentTag){
  if (alignmentTag == null) {
    return new Cue(text);
  }
  @Cue.AnchorType int positionAnchor;
switch (alignmentTag) {
case ALIGN_BOTTOM_LEFT:
case ALIGN_MID_LEFT:
case ALIGN_TOP_LEFT:
    positionAnchor=Cue.ANCHOR_TYPE_START;
  break;
case ALIGN_BOTTOM_RIGHT:
case ALIGN_MID_RIGHT:
case ALIGN_TOP_RIGHT:
positionAnchor=Cue.ANCHOR_TYPE_END;
break;
case ALIGN_BOTTOM_MID:
case ALIGN_MID_MID:
case ALIGN_TOP_MID:
default :
positionAnchor=Cue.ANCHOR_TYPE_MIDDLE;
break;
}
@Cue.AnchorType int lineAnchor;
switch (alignmentTag) {
case ALIGN_BOTTOM_LEFT:
case ALIGN_BOTTOM_MID:
case ALIGN_BOTTOM_RIGHT:
lineAnchor=Cue.ANCHOR_TYPE_END;
break;
case ALIGN_TOP_LEFT:
case ALIGN_TOP_MID:
case ALIGN_TOP_RIGHT:
lineAnchor=Cue.ANCHOR_TYPE_START;
break;
case ALIGN_MID_LEFT:
case ALIGN_MID_MID:
case ALIGN_MID_RIGHT:
default :
lineAnchor=Cue.ANCHOR_TYPE_MIDDLE;
break;
}
return new Cue(text,null,getFractionalPositionForAnchorType(lineAnchor),Cue.LINE_TYPE_FRACTION,lineAnchor,getFractionalPositionForAnchorType(positionAnchor),positionAnchor,Cue.DIMEN_UNSET);
}
