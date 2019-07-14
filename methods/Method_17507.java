private void evict(){
  Node victim=null;
  for (  Node head : headQ) {
    if (head.next != head) {
      victim=head.next;
      break;
    }
  }
  if (victim == null) {
    return;
  }
  victim.remove();
  data.remove(victim.key);
  out.put(victim.key,victim);
  if (out.size() > maxOut) {
    out.remove(out.firstLongKey());
  }
}
