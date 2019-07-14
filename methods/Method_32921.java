public void setChar(int columnToSet,int codePoint,long style){
  mStyle[columnToSet]=style;
  final int newCodePointDisplayWidth=WcWidth.width(codePoint);
  if (!mHasNonOneWidthOrSurrogateChars) {
    if (codePoint >= Character.MIN_SUPPLEMENTARY_CODE_POINT || newCodePointDisplayWidth != 1) {
      mHasNonOneWidthOrSurrogateChars=true;
    }
 else {
      mText[columnToSet]=(char)codePoint;
      return;
    }
  }
  final boolean newIsCombining=newCodePointDisplayWidth <= 0;
  boolean wasExtraColForWideChar=(columnToSet > 0) && wideDisplayCharacterStartingAt(columnToSet - 1);
  if (newIsCombining) {
    if (wasExtraColForWideChar)     columnToSet--;
  }
 else {
    if (wasExtraColForWideChar)     setChar(columnToSet - 1,' ',style);
    boolean overwritingWideCharInNextColumn=newCodePointDisplayWidth == 2 && wideDisplayCharacterStartingAt(columnToSet + 1);
    if (overwritingWideCharInNextColumn)     setChar(columnToSet + 1,' ',style);
  }
  char[] text=mText;
  final int oldStartOfColumnIndex=findStartOfColumn(columnToSet);
  final int oldCodePointDisplayWidth=WcWidth.width(text,oldStartOfColumnIndex);
  int oldCharactersUsedForColumn;
  if (columnToSet + oldCodePointDisplayWidth < mColumns) {
    oldCharactersUsedForColumn=findStartOfColumn(columnToSet + oldCodePointDisplayWidth) - oldStartOfColumnIndex;
  }
 else {
    oldCharactersUsedForColumn=mSpaceUsed - oldStartOfColumnIndex;
  }
  int newCharactersUsedForColumn=Character.charCount(codePoint);
  if (newIsCombining) {
    newCharactersUsedForColumn+=oldCharactersUsedForColumn;
  }
  int oldNextColumnIndex=oldStartOfColumnIndex + oldCharactersUsedForColumn;
  int newNextColumnIndex=oldStartOfColumnIndex + newCharactersUsedForColumn;
  final int javaCharDifference=newCharactersUsedForColumn - oldCharactersUsedForColumn;
  if (javaCharDifference > 0) {
    int oldCharactersAfterColumn=mSpaceUsed - oldNextColumnIndex;
    if (mSpaceUsed + javaCharDifference > text.length) {
      char[] newText=new char[text.length + mColumns];
      System.arraycopy(text,0,newText,0,oldStartOfColumnIndex + oldCharactersUsedForColumn);
      System.arraycopy(text,oldNextColumnIndex,newText,newNextColumnIndex,oldCharactersAfterColumn);
      mText=text=newText;
    }
 else {
      System.arraycopy(text,oldNextColumnIndex,text,newNextColumnIndex,oldCharactersAfterColumn);
    }
  }
 else   if (javaCharDifference < 0) {
    System.arraycopy(text,oldNextColumnIndex,text,newNextColumnIndex,mSpaceUsed - oldNextColumnIndex);
  }
  mSpaceUsed+=javaCharDifference;
  Character.toChars(codePoint,text,oldStartOfColumnIndex + (newIsCombining ? oldCharactersUsedForColumn : 0));
  if (oldCodePointDisplayWidth == 2 && newCodePointDisplayWidth == 1) {
    if (mSpaceUsed + 1 > text.length) {
      char[] newText=new char[text.length + mColumns];
      System.arraycopy(text,0,newText,0,newNextColumnIndex);
      System.arraycopy(text,newNextColumnIndex,newText,newNextColumnIndex + 1,mSpaceUsed - newNextColumnIndex);
      mText=text=newText;
    }
 else {
      System.arraycopy(text,newNextColumnIndex,text,newNextColumnIndex + 1,mSpaceUsed - newNextColumnIndex);
    }
    text[newNextColumnIndex]=' ';
    ++mSpaceUsed;
  }
 else   if (oldCodePointDisplayWidth == 1 && newCodePointDisplayWidth == 2) {
    if (columnToSet == mColumns - 1) {
      throw new IllegalArgumentException("Cannot put wide character in last column");
    }
 else     if (columnToSet == mColumns - 2) {
      mSpaceUsed=(short)newNextColumnIndex;
    }
 else {
      int newNextNextColumnIndex=newNextColumnIndex + (Character.isHighSurrogate(mText[newNextColumnIndex]) ? 2 : 1);
      int nextLen=newNextNextColumnIndex - newNextColumnIndex;
      System.arraycopy(text,newNextNextColumnIndex,text,newNextColumnIndex,mSpaceUsed - newNextNextColumnIndex);
      mSpaceUsed-=nextLen;
    }
  }
}
