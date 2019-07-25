@SuppressWarnings("unchecked") @Toolable @javax.inject.Inject protected void initialize(Injector injector){
  for (  Binding<?> binding : injector.findBindingsByType(advisesType)) {
    Key<?> bindingKey=binding.getKey();
    if (bindingKey.hasAttributes() && AdviceElement.class.isAssignableFrom(bindingKey.getAnnotationType())) {
      AdviceElementImpl adviceElement=(AdviceElementImpl)bindingKey.getAnnotation();
      if (name.equals(adviceElement.name())) {
        if (adviceElement.type() == AdviceElement.Type.ADVICE) {
          adviceBindings.add(new ProvisionAdviceHolder<UnaryOperator<T>>((Binding<UnaryOperator<T>>)binding,adviceElement.getOrder()));
        }
        dependencies.add(Dependency.get(bindingKey));
      }
    }
  }
  adviceBindings.sort(ByOrder);
}
