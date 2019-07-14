/** 
 * Select Graphic Rendition (SGR) - see http://en.wikipedia.org/wiki/ANSI_escape_code#graphics. 
 */
private void selectGraphicRendition(){
  if (mArgIndex >= mArgs.length)   mArgIndex=mArgs.length - 1;
  for (int i=0; i <= mArgIndex; i++) {
    int code=mArgs[i];
    if (code < 0) {
      if (mArgIndex > 0) {
        continue;
      }
 else {
        code=0;
      }
    }
    if (code == 0) {
      mForeColor=TextStyle.COLOR_INDEX_FOREGROUND;
      mBackColor=TextStyle.COLOR_INDEX_BACKGROUND;
      mEffect=0;
    }
 else     if (code == 1) {
      mEffect|=TextStyle.CHARACTER_ATTRIBUTE_BOLD;
    }
 else     if (code == 2) {
      mEffect|=TextStyle.CHARACTER_ATTRIBUTE_DIM;
    }
 else     if (code == 3) {
      mEffect|=TextStyle.CHARACTER_ATTRIBUTE_ITALIC;
    }
 else     if (code == 4) {
      mEffect|=TextStyle.CHARACTER_ATTRIBUTE_UNDERLINE;
    }
 else     if (code == 5) {
      mEffect|=TextStyle.CHARACTER_ATTRIBUTE_BLINK;
    }
 else     if (code == 7) {
      mEffect|=TextStyle.CHARACTER_ATTRIBUTE_INVERSE;
    }
 else     if (code == 8) {
      mEffect|=TextStyle.CHARACTER_ATTRIBUTE_INVISIBLE;
    }
 else     if (code == 9) {
      mEffect|=TextStyle.CHARACTER_ATTRIBUTE_STRIKETHROUGH;
    }
 else     if (code == 10) {
    }
 else     if (code == 11) {
    }
 else     if (code == 22) {
      mEffect&=~(TextStyle.CHARACTER_ATTRIBUTE_BOLD | TextStyle.CHARACTER_ATTRIBUTE_DIM);
    }
 else     if (code == 23) {
      mEffect&=~TextStyle.CHARACTER_ATTRIBUTE_ITALIC;
    }
 else     if (code == 24) {
      mEffect&=~TextStyle.CHARACTER_ATTRIBUTE_UNDERLINE;
    }
 else     if (code == 25) {
      mEffect&=~TextStyle.CHARACTER_ATTRIBUTE_BLINK;
    }
 else     if (code == 27) {
      mEffect&=~TextStyle.CHARACTER_ATTRIBUTE_INVERSE;
    }
 else     if (code == 28) {
      mEffect&=~TextStyle.CHARACTER_ATTRIBUTE_INVISIBLE;
    }
 else     if (code == 29) {
      mEffect&=~TextStyle.CHARACTER_ATTRIBUTE_STRIKETHROUGH;
    }
 else     if (code >= 30 && code <= 37) {
      mForeColor=code - 30;
    }
 else     if (code == 38 || code == 48) {
      if (i + 2 > mArgIndex)       continue;
      int firstArg=mArgs[i + 1];
      if (firstArg == 2) {
        if (i + 4 > mArgIndex) {
          Log.w(EmulatorDebug.LOG_TAG,"Too few CSI" + code + ";2 RGB arguments");
        }
 else {
          int red=mArgs[i + 2], green=mArgs[i + 3], blue=mArgs[i + 4];
          if (red < 0 || green < 0 || blue < 0 || red > 255 || green > 255 || blue > 255) {
            finishSequenceAndLogError("Invalid RGB: " + red + "," + green + "," + blue);
          }
 else {
            int argbColor=0xff000000 | (red << 16) | (green << 8) | blue;
            if (code == 38) {
              mForeColor=argbColor;
            }
 else {
              mBackColor=argbColor;
            }
          }
          i+=4;
        }
      }
 else       if (firstArg == 5) {
        int color=mArgs[i + 2];
        i+=2;
        if (color >= 0 && color < TextStyle.NUM_INDEXED_COLORS) {
          if (code == 38) {
            mForeColor=color;
          }
 else {
            mBackColor=color;
          }
        }
 else {
          if (LOG_ESCAPE_SEQUENCES)           Log.w(EmulatorDebug.LOG_TAG,"Invalid color index: " + color);
        }
      }
 else {
        finishSequenceAndLogError("Invalid ISO-8613-3 SGR first argument: " + firstArg);
      }
    }
 else     if (code == 39) {
      mForeColor=TextStyle.COLOR_INDEX_FOREGROUND;
    }
 else     if (code >= 40 && code <= 47) {
      mBackColor=code - 40;
    }
 else     if (code == 49) {
      mBackColor=TextStyle.COLOR_INDEX_BACKGROUND;
    }
 else     if (code >= 90 && code <= 97) {
      mForeColor=code - 90 + 8;
    }
 else     if (code >= 100 && code <= 107) {
      mBackColor=code - 100 + 8;
    }
 else {
      if (LOG_ESCAPE_SEQUENCES)       Log.w(EmulatorDebug.LOG_TAG,String.format("SGR unknown code %d",code));
    }
  }
}
