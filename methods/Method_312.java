private void delTail(){
  cacheMap.remove(tailer.getKey());
  tailer.next.tail=null;
  tailer=tailer.next;
  nodeCount--;
}
