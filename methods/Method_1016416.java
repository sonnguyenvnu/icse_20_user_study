public void scrape(String text){
  text=text.replace(lb,space).replace(rb,space).replace(dquotes,space);
  int p, q, s=0;
  String u;
  while (s < text.length()) {
    p=Math.min(find(text,"smb://",s),Math.min(find(text,"ftp://",s),Math.min(find(text,"http://",s),find(text,"https://",s))));
    if (p == Integer.MAX_VALUE)     break;
    q=text.indexOf(" ",p + 1);
    u=text.substring(p,q < 0 ? text.length() : q);
    if (u.endsWith("."))     u=u.substring(0,u.length() - 1);
    s=p + 1;
    if (this.blackpattern.matcher(u).matches())     continue;
    try {
      links.put(new MultiProtocolURL(u),PRESENT);
    }
 catch (    final MalformedURLException e) {
    }
  }
}
