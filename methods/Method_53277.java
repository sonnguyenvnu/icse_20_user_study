@Override public OEmbed getOEmbed(OEmbedRequest req) throws TwitterException {
  return factory.createOEmbed(get(conf.getRestBaseURL() + "statuses/oembed.json",req.asHttpParameterArray()));
}
