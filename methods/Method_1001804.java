public void solve(){
  boolean changed=false;
  do {
    changed=false;
    for (    FixedLink link : links) {
      if (link.pushIfNeed()) {
        changed=true;
      }
    }
  }
 while (changed);
}
