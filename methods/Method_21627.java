private void negateWhere(Where where) throws SqlParseException {
  for (  Where sub : where.getWheres()) {
    if (sub instanceof Condition) {
      Condition cond=(Condition)sub;
      cond.setOpear(cond.getOpear().negative());
    }
 else {
      negateWhere(sub);
    }
    sub.setConn(sub.getConn().negative());
  }
}
