public static ArrayList<Handler> consolidate(ArrayList<Handler> list){
  ArrayList<Handler> newList=new ArrayList<Handler>(list.size());
  outer:   for (  Handler c : list) {
    for (    Handler h : newList) {
      if (c.type.equals(h.type) & c.catchBB == h.catchBB) {
        if (h.from == c.to + 1) {
          h.from=c.from;
          continue outer;
        }
 else         if (c.from == h.to + 1) {
          h.to=c.to;
          continue outer;
        }
      }
    }
    newList.add(c);
  }
  Collections.sort(newList,resort);
  return newList;
}
