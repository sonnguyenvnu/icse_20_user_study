private void refresh(){
  ThreadUtils.assertEDT();
  removeAll();
  ViewOptions viewOptions=new ViewOptions(true,false,false,false,false);
  com.intellij.openapi.project.Project project=((MPSProject)getProject()).getProject();
  myUsagesView=new UsagesView(project,viewOptions);
  UsagesView.RerunAction searchTodoAction=new UsagesView.RerunAction(myUsagesView,"Search again");
  myUsagesView.setActions(searchTodoAction,new UsagesView.RebuildAction(myUsagesView),new AnAction("Close","",AllIcons.Actions.Cancel){
    public void actionPerformed(    @NotNull AnActionEvent p0){
      getTool().makeUnavailableLater();
    }
  }
,ActionManager.getInstance().getAction(IdeActions.ACTION_PIN_ACTIVE_TAB));
  add(myUsagesView.getComponent(),BorderLayout.CENTER);
  final Wrappers._T<ProjectScope> scope=new Wrappers._T<ProjectScope>();
  myProject.getRepository().getModelAccess().runReadAction(new Runnable(){
    public void run(){
      scope.value=new ProjectScope(myProject);
    }
  }
);
  searchTodoAction.setRunOptions(FindUtils.makeProvider(new TodoFinder()),new SearchQuery(new GenericHolder<Project>(myProject),scope.value));
  myUsagesView.setCustomNodeRepresentator(new TodoViewer.MyNodeRepresentator());
  searchTodoAction.actionPerformed(AnActionEvent.createFromInputEvent(searchTodoAction,null,ActionPlaces.TODO_VIEW_TOOLBAR));
  getTool().openToolLater(true);
}
