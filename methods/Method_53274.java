@Override public Status updateStatus(String status) throws TwitterException {
  return factory.createStatus(post(conf.getRestBaseURL() + "statuses/update.json",new HttpParameter[]{new HttpParameter("status",status)}));
}
