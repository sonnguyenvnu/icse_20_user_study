void toggle(){
  super.toggle();
  if (link != 0) {
    int i;
    for (i=0; i != sim.elmList.size(); i++) {
      Object o=sim.elmList.elementAt(i);
      if (o instanceof Switch2Elm) {
        Switch2Elm s2=(Switch2Elm)o;
        if (s2.link == link)         s2.position=position;
      }
    }
  }
}
