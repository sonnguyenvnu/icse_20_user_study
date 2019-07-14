/** 
 * Inflates a new hierarchy builder from XML. The builder can then be modified in order to override XML attributes if necessary.
 */
public static GenericDraweeHierarchyBuilder inflateBuilder(Context context,@Nullable AttributeSet attrs){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("GenericDraweeHierarchyBuilder#inflateBuilder");
  }
  Resources resources=context.getResources();
  GenericDraweeHierarchyBuilder builder=new GenericDraweeHierarchyBuilder(resources);
  builder=updateBuilder(builder,context,attrs);
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
  return builder;
}
