public static void Fix(AST ast,PcalSymTab stab) throws PcalFixIDException {
  st=stab;
  FixSym(ast,"");
  if (st.iPC != null)   st.iPC=st.UseThis(PcalSymTab.LABEL,st.iPC,"");
}
