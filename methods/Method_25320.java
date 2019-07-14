/** 
 * Modifies  {@code fixBuilder} to either create a new {@code @SuppressWarnings} element on theclosest suppressible node, or add  {@code warningToSuppress} to that node if there's already a{@code SuppressWarnings} annotation there.
 * @param fixBuilder
 * @param state
 * @param warningToSuppress the warning to be suppressed, without the surrounding annotation. Forexample, to produce  {@code @SuppressWarnings("Foo")}, pass  {@code Foo}.
 * @param lineComment if non-null, the {@code @SuppressWarnings} will be prefixed by a linecomment containing this text. Do not pass leading  {@code //} or include any line breaks.
 * @see #addSuppressWarnings(VisitorState,String,String)
 */
public static void addSuppressWarnings(Builder fixBuilder,VisitorState state,String warningToSuppress,@Nullable String lineComment){
  Tree suppressibleNode=suppressibleNode(state.getPath());
  if (suppressibleNode == null) {
    return;
  }
  SuppressWarnings existingAnnotation=getAnnotation(suppressibleNode,SuppressWarnings.class);
  String suppression=state.getTreeMaker().Literal(CLASS,warningToSuppress).toString();
  Optional<String> formattedLineComment=Optional.ofNullable(lineComment).map(s -> "// " + s + "\n");
  if (existingAnnotation != null) {
    String[] values=existingAnnotation.value();
    if (Arrays.asList(values).contains(warningToSuppress)) {
      return;
    }
    AnnotationTree suppressAnnotationTree=getAnnotationWithSimpleName(findAnnotationsTree(suppressibleNode),SuppressWarnings.class.getSimpleName());
    if (suppressAnnotationTree == null) {
      return;
    }
    fixBuilder.merge(addValuesToAnnotationArgument(suppressAnnotationTree,"value",ImmutableList.of(suppression),state));
    formattedLineComment.ifPresent(lc -> fixBuilder.prefixWith(suppressAnnotationTree,lc));
  }
 else {
    String replacement=formattedLineComment.orElse("") + "@SuppressWarnings(" + suppression + ") ";
    fixBuilder.prefixWith(suppressibleNode,replacement);
  }
}
