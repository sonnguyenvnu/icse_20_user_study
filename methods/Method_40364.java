private List<Keyword> convertListKeyword(List<keyword> in) throws Exception {
  List<Keyword> out=new ArrayList<Keyword>(in == null ? 0 : in.size());
  if (in != null) {
    for (    keyword e : in) {
      Keyword nkw=new Keyword(e.getInternalArg(),convExpr(e.getInternalValue()));
      if (nkw != null) {
        out.add(nkw);
      }
    }
  }
  return out;
}
