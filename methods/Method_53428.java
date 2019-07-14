public static void oldTraditionalDullBoringImplementation(String... dummy){
  TwitterStream stream=TwitterStreamFactory.getSingleton();
  stream.addListener(new StatusAdapter(){
    @Override public void onStatus(    Status status){
      String.format("@%s %s",status.getUser().getScreenName(),status.getText());
    }
    @Override public void onException(    Exception ex){
      ex.printStackTrace();
    }
  }
);
  stream.filter(new FilterQuery(new String[]{"twitter4j","#twitter4j"}));
}
