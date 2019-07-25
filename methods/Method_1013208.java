/** 
 * Explode                                                               
 */
public static AST Explode(AST ast,PcalSymTab symtab) throws PcalTranslateException {
  st=symtab;
  currentProcedure=null;
  if (ast.getClass().equals(AST.UniprocessObj.getClass()))   return ExplodeUniprocess((AST.Uniprocess)ast);
 else   if (ast.getClass().equals(AST.MultiprocessObj.getClass()))   return ExplodeMultiprocess((AST.Multiprocess)ast);
 else {
    PcalDebug.ReportBug("Unexpected AST type.");
    return null;
  }
}
