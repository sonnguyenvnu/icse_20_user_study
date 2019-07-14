public String getArtworkUrl(boolean small){
  TLRPC.Document document=getDocument();
  if (document != null) {
    for (int i=0, N=document.attributes.size(); i < N; i++) {
      TLRPC.DocumentAttribute attribute=document.attributes.get(i);
      if (attribute instanceof TLRPC.TL_documentAttributeAudio) {
        if (attribute.voice) {
          return null;
        }
 else {
          String performer=attribute.performer;
          String title=attribute.title;
          if (!TextUtils.isEmpty(performer)) {
            for (int a=0; a < excludeWords.length; a++) {
              performer=performer.replace(excludeWords[a]," ");
            }
          }
          if (TextUtils.isEmpty(performer) && TextUtils.isEmpty(title)) {
            return null;
          }
          try {
            return "athumb://itunes.apple.com/search?term=" + URLEncoder.encode(performer + " - " + title,"UTF-8") + "&entity=song&limit=4" + (small ? "&s=1" : "");
          }
 catch (          Exception ignore) {
          }
        }
      }
    }
  }
  return null;
}
