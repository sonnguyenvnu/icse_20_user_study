public static void sortEmoji(){
  recentEmoji.clear();
  for (  HashMap.Entry<String,Integer> entry : emojiUseHistory.entrySet()) {
    recentEmoji.add(entry.getKey());
  }
  Collections.sort(recentEmoji,(lhs,rhs) -> {
    Integer count1=emojiUseHistory.get(lhs);
    Integer count2=emojiUseHistory.get(rhs);
    if (count1 == null) {
      count1=0;
    }
    if (count2 == null) {
      count2=0;
    }
    if (count1 > count2) {
      return -1;
    }
 else     if (count1 < count2) {
      return 1;
    }
    return 0;
  }
);
  while (recentEmoji.size() > MAX_RECENT_EMOJI_COUNT) {
    recentEmoji.remove(recentEmoji.size() - 1);
  }
}
