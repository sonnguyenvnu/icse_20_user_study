private void reportMatch(TokenEntry mark1,TokenEntry mark2,int dupes){
  Map<Integer,Match> matches=matchTree.get(dupes);
  if (matches == null) {
    matches=new TreeMap<>();
    matchTree.put(dupes,matches);
    addNewMatch(mark1,mark2,dupes,matches);
  }
 else {
    Match matchA=matchTree.get(dupes).get(mark1.getIndex());
    Match matchB=matchTree.get(dupes).get(mark2.getIndex());
    if (matchA == null && matchB == null) {
      addNewMatch(mark1,mark2,dupes,matches);
    }
 else     if (matchA == null) {
      matchB.addTokenEntry(mark1);
      matches.put(mark1.getIndex(),matchB);
    }
 else     if (matchB == null) {
      matchA.addTokenEntry(mark2);
      matches.put(mark2.getIndex(),matchA);
    }
  }
}
