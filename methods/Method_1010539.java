@NotNull public Element save(@NotNull final DevkitDescriptor descriptor){
  Element root=new _FunctionTypes._return_P0_E0<Element>(){
    public Element invoke(){
      final Element result_raojav_a0a0a0e=new Element("dev-kit");
      result_raojav_a0a0a0e.setAttribute("name",descriptor.getNamespace());
      if (descriptor.getId() != null) {
        result_raojav_a0a0a0e.setAttribute("uuid",descriptor.getId().toString());
      }
      for (      final SModuleReference lang : SetSequence.fromSet(descriptor.getExportedLanguages())) {
        result_raojav_a0a0a0e.addContent(new _FunctionTypes._return_P0_E0<Element>(){
          public Element invoke(){
            final Element result_raojav_a0a0a0a3a0a0a0e=new Element("exported-language");
            result_raojav_a0a0a0a3a0a0a0e.setAttribute("name",lang.toString());
            return result_raojav_a0a0a0a3a0a0a0e;
          }
        }
.invoke());
      }
      if (!(descriptor.getExtendedDevkits().isEmpty())) {
        result_raojav_a0a0a0e.addContent(new _FunctionTypes._return_P0_E0<Element>(){
          public Element invoke(){
            final Element result_raojav_a0a0a0a5a0a0a0e=new Element("extendedDevKits");
            for (            final SModuleReference ref : SetSequence.fromSet(descriptor.getExtendedDevkits())) {
              result_raojav_a0a0a0a5a0a0a0e.addContent(new _FunctionTypes._return_P0_E0<Element>(){
                public Element invoke(){
                  final Element result_raojav_a0a0a0a0a0a0a0a5a0a0a0e=new Element("extendedDevKit");
                  result_raojav_a0a0a0a0a0a0a0a5a0a0a0e.setText(ref.toString());
                  return result_raojav_a0a0a0a0a0a0a0a5a0a0a0e;
                }
              }
.invoke());
            }
            return result_raojav_a0a0a0a5a0a0a0e;
          }
        }
.invoke());
      }
      if (!(descriptor.getExportedSolutions().isEmpty())) {
        result_raojav_a0a0a0e.addContent(new _FunctionTypes._return_P0_E0<Element>(){
          public Element invoke(){
            final Element result_raojav_a0a0a0a7a0a0a0e=new Element("exported-solutions");
            for (            final SModuleReference ref : SetSequence.fromSet(descriptor.getExportedSolutions())) {
              result_raojav_a0a0a0a7a0a0a0e.addContent(new _FunctionTypes._return_P0_E0<Element>(){
                public Element invoke(){
                  final Element result_raojav_a0a0a0a0a0a0a0a7a0a0a0e=new Element("exported-solution");
                  result_raojav_a0a0a0a0a0a0a0a7a0a0a0e.setText(ref.toString());
                  return result_raojav_a0a0a0a0a0a0a0a7a0a0a0e;
                }
              }
.invoke());
            }
            return result_raojav_a0a0a0a7a0a0a0e;
          }
        }
.invoke());
      }
      if (descriptor.getAssociatedGenPlan() != null) {
        Element genPlanElement=new Element("generation-plan");
        genPlanElement.setAttribute("model",PersistenceFacade.getInstance().asString(descriptor.getAssociatedGenPlan()));
        result_raojav_a0a0a0e.addContent(genPlanElement);
      }
      return result_raojav_a0a0a0e;
    }
  }
.invoke();
  return root;
}
