protected void translateStatement(String qid,Statement statement,String pid,boolean add,Writer writer) throws IOException {
  Claim claim=statement.getClaim();
  Value val=claim.getValue();
  ValueVisitor<String> vv=new QSValuePrinter();
  String targetValue=val.accept(vv);
  if (targetValue != null) {
    if (!add) {
      writer.write("- ");
    }
    writer.write(qid + "\t" + pid + "\t" + targetValue);
    for (    SnakGroup q : claim.getQualifiers()) {
      translateSnakGroup(q,false,writer);
    }
    for (    Reference r : statement.getReferences()) {
      for (      SnakGroup g : r.getSnakGroups()) {
        translateSnakGroup(g,true,writer);
      }
      break;
    }
    writer.write("\n");
  }
}
