public static boolean addEntitiesToText(CharSequence text,ArrayList<TLRPC.MessageEntity> entities,boolean out,int type,boolean usernames,boolean photoViewer,boolean useManualParse){
  boolean hasUrls=false;
  if (!(text instanceof Spannable)) {
    return false;
  }
  Spannable spannable=(Spannable)text;
  int count=entities.size();
  URLSpan[] spans=spannable.getSpans(0,text.length(),URLSpan.class);
  if (spans != null && spans.length > 0) {
    hasUrls=true;
  }
  byte t;
  if (photoViewer) {
    t=2;
  }
 else   if (out) {
    t=1;
  }
 else {
    t=0;
  }
  for (int a=0; a < count; a++) {
    TLRPC.MessageEntity entity=entities.get(a);
    if (entity.length <= 0 || entity.offset < 0 || entity.offset >= text.length()) {
      continue;
    }
 else     if (entity.offset + entity.length > text.length()) {
      entity.length=text.length() - entity.offset;
    }
    if (!useManualParse || entity instanceof TLRPC.TL_messageEntityBold || entity instanceof TLRPC.TL_messageEntityItalic || entity instanceof TLRPC.TL_messageEntityCode || entity instanceof TLRPC.TL_messageEntityPre || entity instanceof TLRPC.TL_messageEntityMentionName || entity instanceof TLRPC.TL_inputMessageEntityMentionName) {
      if (spans != null && spans.length > 0) {
        for (int b=0; b < spans.length; b++) {
          if (spans[b] == null) {
            continue;
          }
          int start=spannable.getSpanStart(spans[b]);
          int end=spannable.getSpanEnd(spans[b]);
          if (entity.offset <= start && entity.offset + entity.length >= start || entity.offset <= end && entity.offset + entity.length >= end) {
            spannable.removeSpan(spans[b]);
            spans[b]=null;
          }
        }
      }
    }
    if (entity instanceof TLRPC.TL_messageEntityBold) {
      spannable.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/rmedium.ttf")),entity.offset,entity.offset + entity.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
 else     if (entity instanceof TLRPC.TL_messageEntityItalic) {
      spannable.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/ritalic.ttf")),entity.offset,entity.offset + entity.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
 else     if (entity instanceof TLRPC.TL_messageEntityCode || entity instanceof TLRPC.TL_messageEntityPre) {
      spannable.setSpan(new URLSpanMono(spannable,entity.offset,entity.offset + entity.length,t),entity.offset,entity.offset + entity.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
 else     if (entity instanceof TLRPC.TL_messageEntityMentionName) {
      if (usernames) {
        spannable.setSpan(new URLSpanUserMention("" + ((TLRPC.TL_messageEntityMentionName)entity).user_id,t),entity.offset,entity.offset + entity.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
    }
 else     if (entity instanceof TLRPC.TL_inputMessageEntityMentionName) {
      if (usernames) {
        spannable.setSpan(new URLSpanUserMention("" + ((TLRPC.TL_inputMessageEntityMentionName)entity).user_id.user_id,t),entity.offset,entity.offset + entity.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
    }
 else     if (!useManualParse) {
      String url=TextUtils.substring(text,entity.offset,entity.offset + entity.length);
      if (entity instanceof TLRPC.TL_messageEntityBotCommand) {
        spannable.setSpan(new URLSpanBotCommand(url,t),entity.offset,entity.offset + entity.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
 else       if (entity instanceof TLRPC.TL_messageEntityHashtag || (usernames && entity instanceof TLRPC.TL_messageEntityMention) || entity instanceof TLRPC.TL_messageEntityCashtag) {
        spannable.setSpan(new URLSpanNoUnderline(url),entity.offset,entity.offset + entity.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
 else       if (entity instanceof TLRPC.TL_messageEntityEmail) {
        spannable.setSpan(new URLSpanReplacement("mailto:" + url),entity.offset,entity.offset + entity.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
 else       if (entity instanceof TLRPC.TL_messageEntityUrl) {
        if (Browser.isPassportUrl(entity.url)) {
          continue;
        }
        hasUrls=true;
        if (!url.toLowerCase().startsWith("http") && !url.toLowerCase().startsWith("tg://")) {
          spannable.setSpan(new URLSpanBrowser("http://" + url),entity.offset,entity.offset + entity.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
 else {
          spannable.setSpan(new URLSpanBrowser(url),entity.offset,entity.offset + entity.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
      }
 else       if (entity instanceof TLRPC.TL_messageEntityPhone) {
        hasUrls=true;
        String tel=PhoneFormat.stripExceptNumbers(url);
        if (url.startsWith("+")) {
          tel="+" + tel;
        }
        spannable.setSpan(new URLSpanBrowser("tel:" + tel),entity.offset,entity.offset + entity.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
 else       if (entity instanceof TLRPC.TL_messageEntityTextUrl) {
        if (Browser.isPassportUrl(entity.url)) {
          continue;
        }
        spannable.setSpan(new URLSpanReplacement(entity.url),entity.offset,entity.offset + entity.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
    }
  }
  return hasUrls;
}
