public static Sender create(String url,int timeout){
  return create(url).setTimeout(timeout);
}
