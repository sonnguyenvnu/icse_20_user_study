private void refresh(){
  final int writeTotal=writeSuccessCount() + writeAbortCount();
  final int percentage=(int)((1f * writeAbortCount() / writeTotal) * 100);
  okHttpCacheWriteErrorView.setText(new StringBuilder().append(writeAbortCount()).append(" / ").append(writeTotal).append(" (").append(percentage).append("%)").toString());
  okHttpCacheRequestCountView.setText(String.valueOf(requestCount()));
  okHttpCacheNetworkCountView.setText(String.valueOf(networkCount()));
  okHttpCacheHitCountView.setText(String.valueOf(hitCount()));
}
