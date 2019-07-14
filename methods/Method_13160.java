@Override @SuppressWarnings("unchecked") public void index(API api,Container.Entry entry,Indexes indexes){
  indexes.getIndex("strings").get(TextReader.getText(entry.getInputStream())).add(entry);
}
