public static SMILParElement addPar(SMILDocument document){
  SMILParElement par=(SMILParElement)document.createElement("par");
  par.setDur(8.0f);
  document.getBody().appendChild(par);
  return par;
}
