@Setup public void init() throws Exception {
  Globals.prefs=JabRefPreferences.getInstance();
  Random randomizer=new Random();
  for (int i=0; i < 1000; i++) {
    BibEntry entry=new BibEntry();
    entry.setCiteKey("id" + i);
    entry.setField("title","This is my title " + i);
    entry.setField("author","Firstname Lastname and FirstnameA LastnameA and FirstnameB LastnameB" + i);
    entry.setField("journal","Journal Title " + i);
    entry.setField("keyword","testkeyword");
    entry.setField("year","1" + i);
    entry.setField("rnd","2" + randomizer.nextInt());
    database.insertEntry(entry);
  }
  bibtexString=getOutputWriter().toString();
  latexConversionString="{A} \\textbf{bold} approach {\\it to} ${{\\Sigma}}{\\Delta}$ modulator \\textsuperscript{2} \\$";
  htmlConversionString="<b>&Ouml;sterreich</b> &#8211; &amp; characters &#x2aa2; <i>italic</i>";
}
