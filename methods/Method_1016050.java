@Override public void configure(Binder binder,ExampleAutoBind annotation){
  String value=System.getProperty(annotation.propertyName(),annotation.defaultValue());
  binder.bind(String.class).annotatedWith(annotation).toInstance(value);
}
