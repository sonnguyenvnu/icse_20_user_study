public final tla2sany.st.ParseError[] errors(){
  tla2sany.st.ParseError[] pes=new tla2sany.st.ParseError[loe.size()];
  for (int lvi=0; lvi < pes.length; lvi++)   pes[lvi]=(ParseError)loe.elementAt(lvi);
  return pes;
}
