private DrawingText createLayoutForText(View parentView,CharSequence plainText,TLRPC.RichText richText,int width,int textY,TLRPC.PageBlock parentBlock,Layout.Alignment align,int maxLines,WebpageAdapter parentAdapter){
  if (plainText == null && (richText == null || richText instanceof TLRPC.TL_textEmpty)) {
    return null;
  }
  if (width < 0) {
    width=AndroidUtilities.dp(10);
  }
  int color=getSelectedColor();
  CharSequence text;
  if (plainText != null) {
    text=plainText;
  }
 else {
    text=getText(parentView,richText,richText,parentBlock,width);
  }
  if (TextUtils.isEmpty(text)) {
    return null;
  }
  int additionalSize;
  if (selectedFontSize == 0) {
    additionalSize=-AndroidUtilities.dp(4);
  }
 else   if (selectedFontSize == 1) {
    additionalSize=-AndroidUtilities.dp(2);
  }
 else   if (selectedFontSize == 3) {
    additionalSize=AndroidUtilities.dp(2);
  }
 else   if (selectedFontSize == 4) {
    additionalSize=AndroidUtilities.dp(4);
  }
 else {
    additionalSize=0;
  }
  TextPaint paint;
  if (parentBlock instanceof TLRPC.TL_pageBlockEmbedPost && richText == null) {
    TLRPC.TL_pageBlockEmbedPost pageBlockEmbedPost=(TLRPC.TL_pageBlockEmbedPost)parentBlock;
    if (pageBlockEmbedPost.author == plainText) {
      if (embedPostAuthorPaint == null) {
        embedPostAuthorPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
        embedPostAuthorPaint.setColor(getTextColor());
      }
      embedPostAuthorPaint.setTextSize(AndroidUtilities.dp(15) + additionalSize);
      paint=embedPostAuthorPaint;
    }
 else {
      if (embedPostDatePaint == null) {
        embedPostDatePaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
        if (color == 0) {
          embedPostDatePaint.setColor(0xff8f97a0);
        }
 else {
          embedPostDatePaint.setColor(getGrayTextColor());
        }
      }
      embedPostDatePaint.setTextSize(AndroidUtilities.dp(14) + additionalSize);
      paint=embedPostDatePaint;
    }
  }
 else   if (parentBlock instanceof TLRPC.TL_pageBlockChannel) {
    if (channelNamePaint == null) {
      channelNamePaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
      channelNamePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
    }
    if (channelBlock == null) {
      channelNamePaint.setColor(getTextColor());
    }
 else {
      channelNamePaint.setColor(0xffffffff);
    }
    channelNamePaint.setTextSize(AndroidUtilities.dp(15));
    paint=channelNamePaint;
  }
 else   if (parentBlock instanceof TL_pageBlockRelatedArticlesChild) {
    TL_pageBlockRelatedArticlesChild pageBlockRelatedArticlesChild=(TL_pageBlockRelatedArticlesChild)parentBlock;
    if (plainText == pageBlockRelatedArticlesChild.parent.articles.get(pageBlockRelatedArticlesChild.num).title) {
      if (relatedArticleHeaderPaint == null) {
        relatedArticleHeaderPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
        relatedArticleHeaderPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
      }
      relatedArticleHeaderPaint.setColor(getTextColor());
      relatedArticleHeaderPaint.setTextSize(AndroidUtilities.dp(15) + additionalSize);
      paint=relatedArticleHeaderPaint;
    }
 else {
      if (relatedArticleTextPaint == null) {
        relatedArticleTextPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
      }
      relatedArticleTextPaint.setColor(getGrayTextColor());
      relatedArticleTextPaint.setTextSize(AndroidUtilities.dp(14) + additionalSize);
      paint=relatedArticleTextPaint;
    }
  }
 else   if (isListItemBlock(parentBlock) && plainText != null) {
    if (listTextPointerPaint == null) {
      listTextPointerPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
      listTextPointerPaint.setColor(getTextColor());
    }
    if (listTextNumPaint == null) {
      listTextNumPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
      listTextNumPaint.setColor(getTextColor());
    }
    listTextPointerPaint.setTextSize(AndroidUtilities.dp(19) + additionalSize);
    listTextNumPaint.setTextSize(AndroidUtilities.dp(16) + additionalSize);
    if (parentBlock instanceof TL_pageBlockListItem && !((TL_pageBlockListItem)parentBlock).parent.pageBlockList.ordered) {
      paint=listTextPointerPaint;
    }
 else {
      paint=listTextNumPaint;
    }
  }
 else {
    paint=getTextPaint(richText,richText,parentBlock);
  }
  StaticLayout result;
  if (maxLines != 0) {
    if (parentBlock instanceof TLRPC.TL_pageBlockPullquote) {
      result=StaticLayoutEx.createStaticLayout(text,paint,width,Layout.Alignment.ALIGN_CENTER,1.0f,0.0f,false,TextUtils.TruncateAt.END,width,maxLines);
    }
 else {
      result=StaticLayoutEx.createStaticLayout(text,paint,width,align,1.0f,AndroidUtilities.dp(4),false,TextUtils.TruncateAt.END,width,maxLines);
    }
  }
 else {
    if (text.charAt(text.length() - 1) == '\n') {
      text=text.subSequence(0,text.length() - 1);
    }
    if (parentBlock instanceof TLRPC.TL_pageBlockPullquote) {
      result=new StaticLayout(text,paint,width,Layout.Alignment.ALIGN_CENTER,1.0f,0.0f,false);
    }
 else {
      result=new StaticLayout(text,paint,width,align,1.0f,AndroidUtilities.dp(4),false);
    }
  }
  if (result == null) {
    return null;
  }
  CharSequence finalText=result.getText();
  LinkPath textPath=null;
  LinkPath markPath=null;
  if (result != null && finalText instanceof Spanned) {
    Spanned spanned=(Spanned)finalText;
    try {
      AnchorSpan[] innerSpans=spanned.getSpans(0,spanned.length(),AnchorSpan.class);
      int linesCount=result.getLineCount();
      if (innerSpans != null && innerSpans.length > 0) {
        for (int a=0; a < innerSpans.length; a++) {
          if (linesCount <= 1) {
            parentAdapter.anchorsOffset.put(innerSpans[a].getName(),textY);
          }
 else {
            parentAdapter.anchorsOffset.put(innerSpans[a].getName(),textY + result.getLineTop(result.getLineForOffset(spanned.getSpanStart(innerSpans[a]))));
          }
        }
      }
    }
 catch (    Exception ignore) {
    }
    try {
      TextPaintWebpageUrlSpan[] innerSpans=spanned.getSpans(0,spanned.length(),TextPaintWebpageUrlSpan.class);
      if (innerSpans != null && innerSpans.length > 0) {
        textPath=new LinkPath(true);
        textPath.setAllowReset(false);
        for (int a=0; a < innerSpans.length; a++) {
          int start=spanned.getSpanStart(innerSpans[a]);
          int end=spanned.getSpanEnd(innerSpans[a]);
          textPath.setCurrentLayout(result,start,0);
          int shift=innerSpans[a].getTextPaint() != null ? innerSpans[a].getTextPaint().baselineShift : 0;
          textPath.setBaselineShift(shift != 0 ? shift + AndroidUtilities.dp(shift > 0 ? 5 : -2) : 0);
          result.getSelectionPath(start,end,textPath);
        }
        textPath.setAllowReset(true);
      }
    }
 catch (    Exception ignore) {
    }
    try {
      TextPaintMarkSpan[] innerSpans=spanned.getSpans(0,spanned.length(),TextPaintMarkSpan.class);
      if (innerSpans != null && innerSpans.length > 0) {
        markPath=new LinkPath(true);
        markPath.setAllowReset(false);
        for (int a=0; a < innerSpans.length; a++) {
          int start=spanned.getSpanStart(innerSpans[a]);
          int end=spanned.getSpanEnd(innerSpans[a]);
          markPath.setCurrentLayout(result,start,0);
          int shift=innerSpans[a].getTextPaint() != null ? innerSpans[a].getTextPaint().baselineShift : 0;
          markPath.setBaselineShift(shift != 0 ? shift + AndroidUtilities.dp(shift > 0 ? 5 : -2) : 0);
          result.getSelectionPath(start,end,markPath);
        }
        markPath.setAllowReset(true);
      }
    }
 catch (    Exception ignore) {
    }
  }
  DrawingText drawingText=new DrawingText();
  drawingText.textLayout=result;
  drawingText.textPath=textPath;
  drawingText.markPath=markPath;
  return drawingText;
}
