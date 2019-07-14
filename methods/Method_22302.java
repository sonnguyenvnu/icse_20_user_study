@SuppressWarnings("WeakerAccess") @NonNull protected HttpURLConnection createConnection(@NonNull URL url) throws IOException {
  return (HttpURLConnection)url.openConnection();
}
