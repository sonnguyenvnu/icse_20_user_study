/** 
 * Configures a font substitution catalog
 * @param substitutions font substitutions
 * @throws FOPException if something's wrong with the config data
 */
public void configure(FontSubstitutions substitutions) throws FOPException {
  Configuration[] substitutionCfgs=cfg.getChildren("substitution");
  for (int i=0; i < substitutionCfgs.length; i++) {
    Configuration fromCfg=substitutionCfgs[i].getChild("from",false);
    if (fromCfg == null) {
      throw new FOPException("'substitution' element without child 'from' element");
    }
    Configuration toCfg=substitutionCfgs[i].getChild("to",false);
    if (fromCfg == null) {
      throw new FOPException("'substitution' element without child 'to' element");
    }
    FontQualifier fromQualifier=getQualfierFromConfiguration(fromCfg);
    FontQualifier toQualifier=getQualfierFromConfiguration(toCfg);
    FontSubstitution substitution=new FontSubstitution(fromQualifier,toQualifier);
    substitutions.add(substitution);
  }
}
