/** 
 * Appends formatted text to the source. <p>Formatting supports  {@code %s} and {@code %n$s}. Most args are converted according to their {@link Object#toString()} method, except that:<ul><li>  {@link Package} and {@link PackageElement} instances use their fully-qualified names(no "package " prefix). <li>  {@link Class},  {@link TypeElement},  {@link DeclaredType} and {@link QualifiedName}instances use their qualified names where necessary, or shorter versions if a suitable import line can be added. <li>  {@link Excerpt} instances have {@link Excerpt#addTo(SourceBuilder)} called.</ul>
 */
public SourceBuilder add(String fmt,Object... args){
  TemplateApplier.withParams(args).onText(source::append).onParam(this::add).parse(fmt);
  return this;
}
