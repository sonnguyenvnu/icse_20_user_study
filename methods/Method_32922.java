boolean isBlank(){
  for (int charIndex=0, charLen=getSpaceUsed(); charIndex < charLen; charIndex++)   if (mText[charIndex] != ' ')   return false;
  return true;
}
