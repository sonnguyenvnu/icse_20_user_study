@NonNull @Override public List<ViewAttribute> collect(RecyclerView view,AttributeTranslator attributeTranslator){
  List<ViewAttribute> attributes=new ArrayList<>();
  attributes.add(new ViewAttribute<>("LayoutManager",view.getLayoutManager().getClass().getSimpleName()));
  attributes.add(new ViewAttribute<>("Adapter",view.getAdapter().getClass().getSimpleName()));
  attributes.add(new ViewAttribute<>("FixedSize",view.hasFixedSize()));
  return attributes;
}
