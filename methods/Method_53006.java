@Override public void getOEmbed(final OEmbedRequest req){
  getDispatcher().invokeLater(new AsyncTask(OEMBED,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      OEmbed oembed=twitter.getOEmbed(req);
      for (      TwitterListener listener : listeners) {
        try {
          listener.gotOEmbed(oembed);
        }
 catch (        Exception e) {
          logger.warn("Exception at getOEmbed",e);
        }
      }
    }
  }
);
}
