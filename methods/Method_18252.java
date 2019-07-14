/** 
 * Create a  {@link InternalNode} from an existing {@link Component}.
 * @param c The context to create the layout within
 * @param component The component to render within this layout
 * @param defStyleAttr The id of the attribute to use for default style attributes
 * @param defStyleRes The id of the style to use for layout attributes
 * @return A layout builder
 */
static InternalNode create(ComponentContext c,Component component,@AttrRes int defStyleAttr,@StyleRes int defStyleRes){
  if (component == null) {
    return ComponentContext.NULL_LAYOUT;
  }
  return c.newLayoutBuilder(component,defStyleAttr,defStyleRes);
}
