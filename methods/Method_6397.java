public ArrayList<TLRPC.MessageEntity> getEntities(CharSequence[] message){
  if (message == null || message[0] == null) {
    return null;
  }
  ArrayList<TLRPC.MessageEntity> entities=null;
  int index;
  int start=-1;
  int lastIndex=0;
  boolean isPre=false;
  final String mono="`";
  final String pre="```";
  final String bold="**";
  final String italic="__";
  while ((index=TextUtils.indexOf(message[0],!isPre ? mono : pre,lastIndex)) != -1) {
    if (start == -1) {
      isPre=message[0].length() - index > 2 && message[0].charAt(index + 1) == '`' && message[0].charAt(index + 2) == '`';
      start=index;
      lastIndex=index + (isPre ? 3 : 1);
    }
 else {
      if (entities == null) {
        entities=new ArrayList<>();
      }
      for (int a=index + (isPre ? 3 : 1); a < message[0].length(); a++) {
        if (message[0].charAt(a) == '`') {
          index++;
        }
 else {
          break;
        }
      }
      lastIndex=index + (isPre ? 3 : 1);
      if (isPre) {
        int firstChar=start > 0 ? message[0].charAt(start - 1) : 0;
        boolean replacedFirst=firstChar == ' ' || firstChar == '\n';
        CharSequence startMessage=substring(message[0],0,start - (replacedFirst ? 1 : 0));
        CharSequence content=substring(message[0],start + 3,index);
        firstChar=index + 3 < message[0].length() ? message[0].charAt(index + 3) : 0;
        CharSequence endMessage=substring(message[0],index + 3 + (firstChar == ' ' || firstChar == '\n' ? 1 : 0),message[0].length());
        if (startMessage.length() != 0) {
          startMessage=AndroidUtilities.concat(startMessage,"\n");
        }
 else {
          replacedFirst=true;
        }
        if (endMessage.length() != 0) {
          endMessage=AndroidUtilities.concat("\n",endMessage);
        }
        if (!TextUtils.isEmpty(content)) {
          message[0]=AndroidUtilities.concat(startMessage,content,endMessage);
          TLRPC.TL_messageEntityPre entity=new TLRPC.TL_messageEntityPre();
          entity.offset=start + (replacedFirst ? 0 : 1);
          entity.length=index - start - 3 + (replacedFirst ? 0 : 1);
          entity.language="";
          entities.add(entity);
          lastIndex-=6;
        }
      }
 else {
        if (start + 1 != index) {
          message[0]=AndroidUtilities.concat(substring(message[0],0,start),substring(message[0],start + 1,index),substring(message[0],index + 1,message[0].length()));
          TLRPC.TL_messageEntityCode entity=new TLRPC.TL_messageEntityCode();
          entity.offset=start;
          entity.length=index - start - 1;
          entities.add(entity);
          lastIndex-=2;
        }
      }
      start=-1;
      isPre=false;
    }
  }
  if (start != -1 && isPre) {
    message[0]=AndroidUtilities.concat(substring(message[0],0,start),substring(message[0],start + 2,message[0].length()));
    if (entities == null) {
      entities=new ArrayList<>();
    }
    TLRPC.TL_messageEntityCode entity=new TLRPC.TL_messageEntityCode();
    entity.offset=start;
    entity.length=1;
    entities.add(entity);
  }
  if (message[0] instanceof Spanned) {
    Spanned spannable=(Spanned)message[0];
    TypefaceSpan[] spans=spannable.getSpans(0,message[0].length(),TypefaceSpan.class);
    if (spans != null && spans.length > 0) {
      for (int a=0; a < spans.length; a++) {
        TypefaceSpan span=spans[a];
        int spanStart=spannable.getSpanStart(span);
        int spanEnd=spannable.getSpanEnd(span);
        if (checkInclusion(spanStart,entities) || checkInclusion(spanEnd,entities) || checkIntersection(spanStart,spanEnd,entities)) {
          continue;
        }
        if (entities == null) {
          entities=new ArrayList<>();
        }
        TLRPC.MessageEntity entity;
        if (span.isMono()) {
          entity=new TLRPC.TL_messageEntityCode();
        }
 else         if (span.isBold()) {
          entity=new TLRPC.TL_messageEntityBold();
        }
 else {
          entity=new TLRPC.TL_messageEntityItalic();
        }
        entity.offset=spanStart;
        entity.length=spanEnd - spanStart;
        entities.add(entity);
      }
    }
    URLSpanUserMention[] spansMentions=spannable.getSpans(0,message[0].length(),URLSpanUserMention.class);
    if (spansMentions != null && spansMentions.length > 0) {
      if (entities == null) {
        entities=new ArrayList<>();
      }
      for (int b=0; b < spansMentions.length; b++) {
        TLRPC.TL_inputMessageEntityMentionName entity=new TLRPC.TL_inputMessageEntityMentionName();
        entity.user_id=MessagesController.getInstance(currentAccount).getInputUser(Utilities.parseInt(spansMentions[b].getURL()));
        if (entity.user_id != null) {
          entity.offset=spannable.getSpanStart(spansMentions[b]);
          entity.length=Math.min(spannable.getSpanEnd(spansMentions[b]),message[0].length()) - entity.offset;
          if (message[0].charAt(entity.offset + entity.length - 1) == ' ') {
            entity.length--;
          }
          entities.add(entity);
        }
      }
    }
    URLSpanReplacement[] spansUrlReplacement=spannable.getSpans(0,message[0].length(),URLSpanReplacement.class);
    if (spansUrlReplacement != null && spansUrlReplacement.length > 0) {
      if (entities == null) {
        entities=new ArrayList<>();
      }
      for (int b=0; b < spansUrlReplacement.length; b++) {
        TLRPC.TL_messageEntityTextUrl entity=new TLRPC.TL_messageEntityTextUrl();
        entity.offset=spannable.getSpanStart(spansUrlReplacement[b]);
        entity.length=Math.min(spannable.getSpanEnd(spansUrlReplacement[b]),message[0].length()) - entity.offset;
        entity.url=spansUrlReplacement[b].getURL();
        entities.add(entity);
      }
    }
  }
  for (int c=0; c < 2; c++) {
    lastIndex=0;
    start=-1;
    String checkString=c == 0 ? bold : italic;
    char checkChar=c == 0 ? '*' : '_';
    while ((index=TextUtils.indexOf(message[0],checkString,lastIndex)) != -1) {
      if (start == -1) {
        char prevChar=index == 0 ? ' ' : message[0].charAt(index - 1);
        if (!checkInclusion(index,entities) && (prevChar == ' ' || prevChar == '\n')) {
          start=index;
        }
        lastIndex=index + 2;
      }
 else {
        for (int a=index + 2; a < message[0].length(); a++) {
          if (message[0].charAt(a) == checkChar) {
            index++;
          }
 else {
            break;
          }
        }
        lastIndex=index + 2;
        if (checkInclusion(index,entities) || checkIntersection(start,index,entities)) {
          start=-1;
          continue;
        }
        if (start + 2 != index) {
          if (entities == null) {
            entities=new ArrayList<>();
          }
          try {
            message[0]=AndroidUtilities.concat(substring(message[0],0,start),substring(message[0],start + 2,index),substring(message[0],index + 2,message[0].length()));
          }
 catch (          Exception e) {
            message[0]=substring(message[0],0,start).toString() + substring(message[0],start + 2,index).toString() + substring(message[0],index + 2,message[0].length()).toString();
          }
          TLRPC.MessageEntity entity;
          if (c == 0) {
            entity=new TLRPC.TL_messageEntityBold();
          }
 else {
            entity=new TLRPC.TL_messageEntityItalic();
          }
          entity.offset=start;
          entity.length=index - start - 2;
          removeOffsetAfter(entity.offset + entity.length,4,entities);
          entities.add(entity);
          lastIndex-=4;
        }
        start=-1;
      }
    }
  }
  return entities;
}
