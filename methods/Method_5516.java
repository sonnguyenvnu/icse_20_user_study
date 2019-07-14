private void handleMiscCode(byte cc2){
switch (cc2) {
case CTRL_ROLL_UP_CAPTIONS_2_ROWS:
    setCaptionMode(CC_MODE_ROLL_UP);
  setCaptionRowCount(2);
return;
case CTRL_ROLL_UP_CAPTIONS_3_ROWS:
setCaptionMode(CC_MODE_ROLL_UP);
setCaptionRowCount(3);
return;
case CTRL_ROLL_UP_CAPTIONS_4_ROWS:
setCaptionMode(CC_MODE_ROLL_UP);
setCaptionRowCount(4);
return;
case CTRL_RESUME_CAPTION_LOADING:
setCaptionMode(CC_MODE_POP_ON);
return;
case CTRL_RESUME_DIRECT_CAPTIONING:
setCaptionMode(CC_MODE_PAINT_ON);
return;
default :
break;
}
if (captionMode == CC_MODE_UNKNOWN) {
return;
}
switch (cc2) {
case CTRL_ERASE_DISPLAYED_MEMORY:
cues=Collections.emptyList();
if (captionMode == CC_MODE_ROLL_UP || captionMode == CC_MODE_PAINT_ON) {
resetCueBuilders();
}
break;
case CTRL_ERASE_NON_DISPLAYED_MEMORY:
resetCueBuilders();
break;
case CTRL_END_OF_CAPTION:
cues=getDisplayCues();
resetCueBuilders();
break;
case CTRL_CARRIAGE_RETURN:
if (captionMode == CC_MODE_ROLL_UP && !currentCueBuilder.isEmpty()) {
currentCueBuilder.rollUp();
}
break;
case CTRL_BACKSPACE:
currentCueBuilder.backspace();
break;
case CTRL_DELETE_TO_END_OF_ROW:
break;
default :
break;
}
}
