public void setMapping(Type from,Type to){
  if (arrows.isEmpty()) {
    arrows.add(new Arrow(from,to));
  }
 else {
    List<Arrow> newArrows=new ArrayList<Arrow>();
    for (    Arrow a : arrows) {
      if (!Type.subtypeOf(from,a.from)) {
        newArrows.add(a);
      }
    }
    arrows=newArrows;
    if (!contains(from)) {
      arrows.add(new Arrow(from,to));
    }
  }
}
