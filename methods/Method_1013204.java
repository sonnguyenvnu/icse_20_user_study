/** 
 * The main translation method.  Should go in a new .java file.      Note that this requires RemoveNameConflicts to be called first    because of the grotty use of the class variable st.              
 */
public Vector<String> translate() throws RemoveNameConflictsException {
  Vector<String> result=new Vector<String>();
  AST xast=null;
  for (int i=0; i < st.disambiguateReport.size(); i++)   result.addElement((String)st.disambiguateReport.elementAt(i));
  try {
    xast=PcalTranslate.Explode(ast,st);
  }
 catch (  PcalTranslateException e) {
    throw new RemoveNameConflictsException(e);
  }
  try {
    PcalTLAGen tlaGenerator=new PcalTLAGen();
    result=tlaGenerator.generate(xast,st,result);
  }
 catch (  PcalTLAGenException e) {
    throw new RemoveNameConflictsException(e);
  }
  try {
    if (ParseAlgorithm.hasDefaultInitialization) {
      st.CheckForDefaultInitValue();
    }
  }
 catch (  PcalSymTabException e) {
    throw new RemoveNameConflictsException(e.getMessage());
  }
  return result;
}
