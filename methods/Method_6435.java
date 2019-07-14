public static String fixEmoji(String emoji){
  char ch;
  int length=emoji.length();
  for (int a=0; a < length; a++) {
    ch=emoji.charAt(a);
    if (ch >= 0xD83C && ch <= 0xD83E) {
      if (ch == 0xD83C && a < length - 1) {
        ch=emoji.charAt(a + 1);
        if (ch == 0xDE2F || ch == 0xDC04 || ch == 0xDE1A || ch == 0xDD7F) {
          emoji=emoji.substring(0,a + 2) + "\uFE0F" + emoji.substring(a + 2);
          length++;
          a+=2;
        }
 else {
          a++;
        }
      }
 else {
        a++;
      }
    }
 else     if (ch == 0x20E3) {
      return emoji;
    }
 else     if (ch >= 0x203C && ch <= 0x3299) {
      if (EmojiData.emojiToFE0FMap.containsKey(ch)) {
        emoji=emoji.substring(0,a + 1) + "\uFE0F" + emoji.substring(a + 1);
        length++;
        a++;
      }
    }
  }
  return emoji;
}
