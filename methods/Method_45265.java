private ImmutableMap<String,Object> toVariableString(final Request request){
  return copyOf(Maps.transformEntries(this.variables,new Maps.EntryTransformer<String,Variable,Object>(){
    @Override public Object transformEntry(    final String key,    final Variable value){
      return value.toTemplateVariable(request);
    }
  }
));
}
