@Override public final void update(final AnActionEvent e){
  super.update(e);
  ActionPlace place=e.getData(MPSCommonDataKeys.PLACE);
  if (e.getInputEvent() instanceof KeyEvent) {
    if (!getPlaces().contains(null)) {
      if (!getPlaces().contains(place)) {
        disable(e.getPresentation());
        return;
      }
    }
  }
  final Project eventProject=getEventProject(e);
  if (myDisableOnNoProject && eventProject == null) {
    disable(e.getPresentation());
    return;
  }
  if (eventProject != null && eventProject.isDisposed()) {
    disable(e.getPresentation());
    return;
  }
  getModelAccess(e).runReadAction(new Runnable(){
    @Override public void run(){
      Map<String,Object> params=new CollectActionData(e).compute();
      if (params == null) {
        disable(e.getPresentation());
        return;
      }
      try {
        doUpdate(e,params);
      }
 catch (      RuntimeException ex) {
        final Logger log=LogManager.getLogger(getClass());
        if (log.isEnabledFor(Level.ERROR)) {
          log.error(String.format("User's action doUpdate method failed. Action: %s. Class: %s",getTemplatePresentation().getText(),BaseAction.this.getClass().getName()),ex);
        }
        disable(e.getPresentation());
      }
    }
  }
);
}
