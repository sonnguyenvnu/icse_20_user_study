@Override protected void populate(Collection<ReplaceText> replacment){
  replacment.add(new ReplaceText("\r\n","</w:t><w:br/><w:t xml:space=\"preserve\">"));
  replacment.add(new ReplaceText("\n","</w:t><w:br/><w:t xml:space=\"preserve\">"));
  replacment.add(new ReplaceText("\t","</w:t><w:tab/><w:t xml:space=\"preserve\">"));
}
