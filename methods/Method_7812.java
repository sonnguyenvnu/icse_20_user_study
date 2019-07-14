public void addHashtagsFromMessage(CharSequence message){
  if (message == null) {
    return;
  }
  boolean changed=false;
  Pattern pattern=Pattern.compile("(^|\\s)#[\\w@.]+");
  Matcher matcher=pattern.matcher(message);
  while (matcher.find()) {
    int start=matcher.start();
    int end=matcher.end();
    if (message.charAt(start) != '@' && message.charAt(start) != '#') {
      start++;
    }
    String hashtag=message.subSequence(start,end).toString();
    if (hashtagsByText == null) {
      hashtagsByText=new HashMap<>();
      hashtags=new ArrayList<>();
    }
    HashtagObject hashtagObject=hashtagsByText.get(hashtag);
    if (hashtagObject == null) {
      hashtagObject=new HashtagObject();
      hashtagObject.hashtag=hashtag;
      hashtagsByText.put(hashtagObject.hashtag,hashtagObject);
    }
 else {
      hashtags.remove(hashtagObject);
    }
    hashtagObject.date=(int)(System.currentTimeMillis() / 1000);
    hashtags.add(0,hashtagObject);
    changed=true;
  }
  if (changed) {
    putRecentHashtags(hashtags);
  }
}
