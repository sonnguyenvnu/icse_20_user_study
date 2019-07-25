public static void create(final @NotNull Editor editor,@NotNull Collection<String> services,final @NotNull Callback callback){
  final JBList<String> list=new JBList<>(services);
  JBPopupFactory.getInstance().createListPopupBuilder(list).setTitle("Symfony: Service Suggestion").setItemChoosenCallback(() -> new WriteCommandAction.Simple(editor.getProject(),"Service Suggestion Insert"){
    @Override protected void run(){
      callback.insert((String)list.getSelectedValue());
    }
  }
.execute()).createPopup().showInBestPositionFor(editor);
}
