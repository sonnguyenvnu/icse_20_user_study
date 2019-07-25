private void init(ReadLineNumbered includer) throws IOException {
  StringLocated s=null;
  List<StringLocated> current2=null;
  boolean paused=false;
  while ((s=includer.readLine()) != null) {
    if (StartUtils.isArobaseStartDiagram(s.getString())) {
      current2=new ArrayList<StringLocated>();
      paused=false;
    }
    if (StartUtils.isArobasePauseDiagram(s.getString())) {
      paused=true;
      reader2.setPaused(true);
    }
    if (StartUtils.isExit(s.getString())) {
      paused=true;
      reader2.setPaused(true);
    }
    if (current2 != null && paused == false) {
      current2.add(s);
    }
 else     if (paused) {
      final StringLocated append=StartUtils.getPossibleAppend(s);
      if (append != null) {
        current2.add(append);
      }
    }
    if (StartUtils.isArobaseUnpauseDiagram(s.getString())) {
      paused=false;
      reader2.setPaused(false);
    }
    if (StartUtils.isArobaseEndDiagram(s.getString()) && current2 != null) {
      if (paused) {
        current2.add(s);
      }
      blocks.add(new BlockUml(current2,defines.cloneMe(),null,this));
      current2=null;
      reader2.setPaused(false);
    }
  }
}
