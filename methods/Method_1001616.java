public synchronized void delete(String fileName){
  if (fileName.contains("*")) {
    incoming.clear();
    outgoing.clear();
    futureOutgoing.clear();
  }
 else {
    incoming.remove(fileName);
    outgoing.remove(fileName);
    futureOutgoing.add(fileName);
  }
}
