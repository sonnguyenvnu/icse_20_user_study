@NonNull @Override public List<ViewAttribute> collect(BottomNavigationView view,AttributeTranslator attributeTranslator){
  final Context context=view.getContext();
  final Resources res=context.getResources();
  List<ViewAttribute> attributes=new ArrayList<>();
  attributes.add(new ViewAttribute<>("MaxItemCount",view.getMaxItemCount()));
  attributes.add(Collectors.createColorAttribute(view,"ItemTint",view.getItemIconTintList()));
  attributes.add(Collectors.createColorAttribute(view,"ItemTextColor",view.getItemTextColor()));
  attributes.add(new ViewAttribute<>("SelectedItemId",new ResourceValue(res,view.getSelectedItemId())));
  attributes.add(new ViewAttribute<>("ItemBackgroundRes",new ResourceValue(res,view.getItemBackgroundResource())));
  attributes.addAll(Collectors.createMenuAttributes(context,view.getMenu()));
  return attributes;
}
