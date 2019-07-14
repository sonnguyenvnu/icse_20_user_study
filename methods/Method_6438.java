public static CharSequence replaceEmoji(CharSequence cs,Paint.FontMetricsInt fontMetrics,int size,boolean createNew,int[] emojiOnly){
  if (SharedConfig.useSystemEmoji || cs == null || cs.length() == 0) {
    return cs;
  }
  Spannable s;
  if (!createNew && cs instanceof Spannable) {
    s=(Spannable)cs;
  }
 else {
    s=Spannable.Factory.getInstance().newSpannable(cs.toString());
  }
  long buf=0;
  int emojiCount=0;
  char c;
  int startIndex=-1;
  int startLength=0;
  int previousGoodIndex=0;
  StringBuilder emojiCode=new StringBuilder(16);
  StringBuilder addionalCode=new StringBuilder(2);
  boolean nextIsSkinTone;
  EmojiDrawable drawable;
  EmojiSpan span;
  int length=cs.length();
  boolean doneEmoji=false;
  int nextValidLength;
  boolean nextValid;
  try {
    for (int i=0; i < length; i++) {
      c=cs.charAt(i);
      if (c >= 0xD83C && c <= 0xD83E || (buf != 0 && (buf & 0xFFFFFFFF00000000L) == 0 && (buf & 0xFFFF) == 0xD83C && (c >= 0xDDE6 && c <= 0xDDFF))) {
        if (startIndex == -1) {
          startIndex=i;
        }
        emojiCode.append(c);
        startLength++;
        buf<<=16;
        buf|=c;
      }
 else       if (emojiCode.length() > 0 && (c == 0x2640 || c == 0x2642 || c == 0x2695)) {
        emojiCode.append(c);
        startLength++;
        buf=0;
        doneEmoji=true;
      }
 else       if (buf > 0 && (c & 0xF000) == 0xD000) {
        emojiCode.append(c);
        startLength++;
        buf=0;
        doneEmoji=true;
      }
 else       if (c == 0x20E3) {
        if (i > 0) {
          char c2=cs.charAt(previousGoodIndex);
          if ((c2 >= '0' && c2 <= '9') || c2 == '#' || c2 == '*') {
            startIndex=previousGoodIndex;
            startLength=i - previousGoodIndex + 1;
            emojiCode.append(c2);
            emojiCode.append(c);
            doneEmoji=true;
          }
        }
      }
 else       if ((c == 0x00A9 || c == 0x00AE || c >= 0x203C && c <= 0x3299) && EmojiData.dataCharsMap.containsKey(c)) {
        if (startIndex == -1) {
          startIndex=i;
        }
        startLength++;
        emojiCode.append(c);
        doneEmoji=true;
      }
 else       if (startIndex != -1) {
        emojiCode.setLength(0);
        startIndex=-1;
        startLength=0;
        doneEmoji=false;
      }
 else       if (c != 0xfe0f) {
        if (emojiOnly != null) {
          emojiOnly[0]=0;
          emojiOnly=null;
        }
      }
      if (doneEmoji && i + 2 < length) {
        char next=cs.charAt(i + 1);
        if (next == 0xD83C) {
          next=cs.charAt(i + 2);
          if (next >= 0xDFFB && next <= 0xDFFF) {
            emojiCode.append(cs.subSequence(i + 1,i + 3));
            startLength+=2;
            i+=2;
          }
        }
 else         if (emojiCode.length() >= 2 && emojiCode.charAt(0) == 0xD83C && emojiCode.charAt(1) == 0xDFF4 && next == 0xDB40) {
          i++;
          while (true) {
            emojiCode.append(cs.subSequence(i,i + 2));
            startLength+=2;
            i+=2;
            if (i >= cs.length() || cs.charAt(i) != 0xDB40) {
              i--;
              break;
            }
          }
        }
      }
      previousGoodIndex=i;
      char prevCh=c;
      for (int a=0; a < 3; a++) {
        if (i + 1 < length) {
          c=cs.charAt(i + 1);
          if (a == 1) {
            if (c == 0x200D && emojiCode.length() > 0) {
              emojiCode.append(c);
              i++;
              startLength++;
              doneEmoji=false;
            }
          }
 else           if (startIndex != -1 || prevCh == '*' || prevCh >= '1' && prevCh <= '9') {
            if (c >= 0xFE00 && c <= 0xFE0F) {
              i++;
              startLength++;
            }
          }
        }
      }
      if (doneEmoji && i + 2 < length && cs.charAt(i + 1) == 0xD83C) {
        char next=cs.charAt(i + 2);
        if (next >= 0xDFFB && next <= 0xDFFF) {
          emojiCode.append(cs.subSequence(i + 1,i + 3));
          startLength+=2;
          i+=2;
        }
      }
      if (doneEmoji) {
        if (emojiOnly != null) {
          emojiOnly[0]++;
        }
        CharSequence code=emojiCode.subSequence(0,emojiCode.length());
        drawable=Emoji.getEmojiDrawable(code);
        if (drawable != null) {
          span=new EmojiSpan(drawable,DynamicDrawableSpan.ALIGN_BOTTOM,size,fontMetrics);
          s.setSpan(span,startIndex,startIndex + startLength,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          emojiCount++;
        }
        startLength=0;
        startIndex=-1;
        emojiCode.setLength(0);
        doneEmoji=false;
      }
      if (Build.VERSION.SDK_INT < 23 && emojiCount >= 50) {
        break;
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
    return cs;
  }
  return s;
}
