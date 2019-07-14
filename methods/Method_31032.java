private static void applyLink(Link link,Spannable spannable){
  spannable.setSpan(new UriSpan(link.url),link.start,link.end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
}
