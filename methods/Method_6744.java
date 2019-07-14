public void generateLayout(TLRPC.User fromUser){
  if (type != 0 || messageOwner.to_id == null || TextUtils.isEmpty(messageText)) {
    return;
  }
  generateLinkDescription();
  textLayoutBlocks=new ArrayList<>();
  textWidth=0;
  boolean hasEntities;
  if (messageOwner.send_state != MESSAGE_SEND_STATE_SENT) {
    hasEntities=false;
    for (int a=0; a < messageOwner.entities.size(); a++) {
      if (!(messageOwner.entities.get(a) instanceof TLRPC.TL_inputMessageEntityMentionName)) {
        hasEntities=true;
        break;
      }
    }
  }
 else {
    hasEntities=!messageOwner.entities.isEmpty();
  }
  boolean useManualParse=!hasEntities && (eventId != 0 || messageOwner instanceof TLRPC.TL_message_old || messageOwner instanceof TLRPC.TL_message_old2 || messageOwner instanceof TLRPC.TL_message_old3 || messageOwner instanceof TLRPC.TL_message_old4 || messageOwner instanceof TLRPC.TL_messageForwarded_old || messageOwner instanceof TLRPC.TL_messageForwarded_old2 || messageOwner instanceof TLRPC.TL_message_secret || messageOwner.media instanceof TLRPC.TL_messageMediaInvoice || isOut() && messageOwner.send_state != MESSAGE_SEND_STATE_SENT || messageOwner.id < 0 || messageOwner.media instanceof TLRPC.TL_messageMediaUnsupported);
  if (useManualParse) {
    addLinks(isOutOwner(),messageText);
  }
 else {
    if (messageText instanceof Spannable && messageText.length() < 1000) {
      try {
        Linkify.addLinks((Spannable)messageText,Linkify.PHONE_NUMBERS);
      }
 catch (      Throwable e) {
        FileLog.e(e);
      }
    }
  }
  boolean hasUrls=addEntitiesToText(messageText,useManualParse);
  int maxWidth=getMaxMessageTextWidth();
  StaticLayout textLayout;
  TextPaint paint;
  if (messageOwner.media instanceof TLRPC.TL_messageMediaGame) {
    paint=Theme.chat_msgGameTextPaint;
  }
 else {
    paint=Theme.chat_msgTextPaint;
  }
  try {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      textLayout=StaticLayout.Builder.obtain(messageText,0,messageText.length(),paint,maxWidth).setBreakStrategy(StaticLayout.BREAK_STRATEGY_HIGH_QUALITY).setHyphenationFrequency(StaticLayout.HYPHENATION_FREQUENCY_NONE).setAlignment(Layout.Alignment.ALIGN_NORMAL).build();
    }
 else {
      textLayout=new StaticLayout(messageText,paint,maxWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
    return;
  }
  textHeight=textLayout.getHeight();
  linesCount=textLayout.getLineCount();
  int blocksCount;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    blocksCount=1;
  }
 else {
    blocksCount=(int)Math.ceil((float)linesCount / LINES_PER_BLOCK);
  }
  int linesOffset=0;
  float prevOffset=0;
  for (int a=0; a < blocksCount; a++) {
    int currentBlockLinesCount;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      currentBlockLinesCount=linesCount;
    }
 else {
      currentBlockLinesCount=Math.min(LINES_PER_BLOCK,linesCount - linesOffset);
    }
    TextLayoutBlock block=new TextLayoutBlock();
    if (blocksCount == 1) {
      block.textLayout=textLayout;
      block.textYOffset=0;
      block.charactersOffset=0;
      if (emojiOnlyCount != 0) {
switch (emojiOnlyCount) {
case 1:
          textHeight-=AndroidUtilities.dp(5.3f);
        block.textYOffset-=AndroidUtilities.dp(5.3f);
      break;
case 2:
    textHeight-=AndroidUtilities.dp(4.5f);
  block.textYOffset-=AndroidUtilities.dp(4.5f);
break;
case 3:
textHeight-=AndroidUtilities.dp(4.2f);
block.textYOffset-=AndroidUtilities.dp(4.2f);
break;
}
}
block.height=textHeight;
}
 else {
int startCharacter=textLayout.getLineStart(linesOffset);
int endCharacter=textLayout.getLineEnd(linesOffset + currentBlockLinesCount - 1);
if (endCharacter < startCharacter) {
continue;
}
block.charactersOffset=startCharacter;
block.charactersEnd=endCharacter;
try {
if (hasUrls && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
block.textLayout=StaticLayout.Builder.obtain(messageText,startCharacter,endCharacter,paint,maxWidth + AndroidUtilities.dp(2)).setBreakStrategy(StaticLayout.BREAK_STRATEGY_HIGH_QUALITY).setHyphenationFrequency(StaticLayout.HYPHENATION_FREQUENCY_NONE).setAlignment(Layout.Alignment.ALIGN_NORMAL).build();
}
 else {
block.textLayout=new StaticLayout(messageText,startCharacter,endCharacter,paint,maxWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
}
block.textYOffset=textLayout.getLineTop(linesOffset);
if (a != 0) {
block.height=(int)(block.textYOffset - prevOffset);
}
block.height=Math.max(block.height,block.textLayout.getLineBottom(block.textLayout.getLineCount() - 1));
prevOffset=block.textYOffset;
}
 catch (Exception e) {
FileLog.e(e);
continue;
}
if (a == blocksCount - 1) {
currentBlockLinesCount=Math.max(currentBlockLinesCount,block.textLayout.getLineCount());
try {
textHeight=Math.max(textHeight,(int)(block.textYOffset + block.textLayout.getHeight()));
}
 catch (Exception e) {
FileLog.e(e);
}
}
}
textLayoutBlocks.add(block);
float lastLeft;
try {
lastLeft=block.textLayout.getLineLeft(currentBlockLinesCount - 1);
if (a == 0 && lastLeft >= 0) {
textXOffset=lastLeft;
}
}
 catch (Exception e) {
lastLeft=0;
if (a == 0) {
textXOffset=0;
}
FileLog.e(e);
}
float lastLine;
try {
lastLine=block.textLayout.getLineWidth(currentBlockLinesCount - 1);
}
 catch (Exception e) {
lastLine=0;
FileLog.e(e);
}
int linesMaxWidth=(int)Math.ceil(lastLine);
if (linesMaxWidth > maxWidth + 80) {
linesMaxWidth=maxWidth;
}
int lastLineWidthWithLeft;
int linesMaxWidthWithLeft;
if (a == blocksCount - 1) {
lastLineWidth=linesMaxWidth;
}
linesMaxWidthWithLeft=lastLineWidthWithLeft=(int)Math.ceil(lastLine + lastLeft);
if (currentBlockLinesCount > 1) {
boolean hasNonRTL=false;
float textRealMaxWidth=0, textRealMaxWidthWithLeft=0, lineWidth, lineLeft;
for (int n=0; n < currentBlockLinesCount; n++) {
try {
lineWidth=block.textLayout.getLineWidth(n);
}
 catch (Exception e) {
FileLog.e(e);
lineWidth=0;
}
if (lineWidth > maxWidth + 20) {
lineWidth=maxWidth;
}
try {
lineLeft=block.textLayout.getLineLeft(n);
}
 catch (Exception e) {
FileLog.e(e);
lineLeft=0;
}
if (lineLeft > 0) {
textXOffset=Math.min(textXOffset,lineLeft);
block.directionFlags|=1;
hasRtl=true;
}
 else {
block.directionFlags|=2;
}
try {
if (!hasNonRTL && lineLeft == 0 && block.textLayout.getParagraphDirection(n) == Layout.DIR_LEFT_TO_RIGHT) {
hasNonRTL=true;
}
}
 catch (Exception ignore) {
hasNonRTL=true;
}
textRealMaxWidth=Math.max(textRealMaxWidth,lineWidth);
textRealMaxWidthWithLeft=Math.max(textRealMaxWidthWithLeft,lineWidth + lineLeft);
linesMaxWidth=Math.max(linesMaxWidth,(int)Math.ceil(lineWidth));
linesMaxWidthWithLeft=Math.max(linesMaxWidthWithLeft,(int)Math.ceil(lineWidth + lineLeft));
}
if (hasNonRTL) {
textRealMaxWidth=textRealMaxWidthWithLeft;
if (a == blocksCount - 1) {
lastLineWidth=lastLineWidthWithLeft;
}
}
 else if (a == blocksCount - 1) {
lastLineWidth=linesMaxWidth;
}
textWidth=Math.max(textWidth,(int)Math.ceil(textRealMaxWidth));
}
 else {
if (lastLeft > 0) {
textXOffset=Math.min(textXOffset,lastLeft);
if (textXOffset == 0) {
linesMaxWidth+=lastLeft;
}
hasRtl=blocksCount != 1;
block.directionFlags|=1;
}
 else {
block.directionFlags|=2;
}
textWidth=Math.max(textWidth,Math.min(maxWidth,linesMaxWidth));
}
linesOffset+=currentBlockLinesCount;
}
}
