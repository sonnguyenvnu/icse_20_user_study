/** 
 * Initializes font settings from the user configuration
 * @param fontManager a font manager
 * @param strict true if strict checking of the configuration is enabled
 * @throws FOPException if an exception occurs while processing the configuration
 */
public void configure(FontManager fontManager,boolean strict) throws FOPException {
  if (cfg.getChild("use-cache",false) != null) {
    try {
      fontManager.setUseCache(cfg.getChild("use-cache").getValueAsBoolean());
    }
 catch (    ConfigurationException mfue) {
      LogUtil.handleException(log,mfue,true);
    }
  }
  if (cfg.getChild("font-base",false) != null) {
    try {
      fontManager.setFontBaseURL(cfg.getChild("font-base").getValue(null));
    }
 catch (    MalformedURLException mfue) {
      LogUtil.handleException(log,mfue,true);
    }
  }
  Configuration fontsCfg=cfg.getChild("fonts",false);
  if (fontsCfg != null) {
    Configuration substitutionsCfg=fontsCfg.getChild("substitutions",false);
    if (substitutionsCfg != null) {
      FontSubstitutionsConfigurator fontSubstitutionsConfigurator=new FontSubstitutionsConfigurator(substitutionsCfg);
      FontSubstitutions substitutions=new FontSubstitutions();
      fontSubstitutionsConfigurator.configure(substitutions);
      fontManager.setFontSubstitutions(substitutions);
    }
    Configuration referencedFontsCfg=fontsCfg.getChild("referenced-fonts",false);
    if (referencedFontsCfg != null) {
      createReferencedFontsMatcher(referencedFontsCfg,strict,fontManager);
    }
  }
}
