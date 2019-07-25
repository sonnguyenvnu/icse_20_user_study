/** 
 * Maps indexed files content to the  {@link IgnoreEntryOccurrence}.
 * @param inputData indexed file data
 * @return {@link IgnoreEntryOccurrence} data mapped with {@link IgnoreFileTypeKey}
 */
@NotNull @Override public Map<IgnoreFileTypeKey,IgnoreEntryOccurrence> map(@NotNull final FileContent inputData){
  if (!(inputData.getPsiFile() instanceof IgnoreFile)) {
    return Collections.emptyMap();
  }
  final ArrayList<Pair<String,Boolean>> items=ContainerUtil.newArrayList();
  inputData.getPsiFile().acceptChildren(new IgnoreVisitor(){
    @Override public void visitEntry(    @NotNull IgnoreEntry entry){
      final String regex=Glob.getRegex(entry.getValue(),entry.getSyntax(),false);
      items.add(Pair.create(regex,entry.isNegated()));
    }
  }
);
  return Collections.singletonMap(new IgnoreFileTypeKey((IgnoreFileType)inputData.getFileType()),new IgnoreEntryOccurrence(inputData.getFile().getUrl(),items));
}
