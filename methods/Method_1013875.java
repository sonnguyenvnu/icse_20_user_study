/** 
 * Initializing method.
 * @param bundleContext bundle's context
 * @param ruleRegistry
 * @param templateRegistry
 */
public void initialize(BundleContext bundleContext,ModuleTypeRegistry moduleTypeRegistry,TemplateRegistry<RuleTemplate> templateRegistry,RuleRegistry ruleRegistry){
  moduleTypeProvider=new CommandlineModuleTypeProvider(bundleContext,moduleTypeRegistry);
  templateProvider=new CommandlineTemplateProvider(bundleContext,templateRegistry);
  ruleImporter=new CommandlineRuleImporter(bundleContext,ruleRegistry);
}
