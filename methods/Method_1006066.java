@FXML private void initialize(){
  viewModel=new ImportEntriesViewModel(task,taskExecutor,database,dialogService,undoManager,preferences,stateManager,fileUpdateMonitor);
  Label placeholder=new Label();
  placeholder.textProperty().bind(viewModel.messageProperty());
  entriesListView.setPlaceholder(placeholder);
  entriesListView.setItems(viewModel.getEntries());
  PseudoClass entrySelected=PseudoClass.getPseudoClass("entry-selected");
  new ViewModelListCellFactory<BibEntry>().withGraphic(entry -> {
    ToggleButton addToggle=IconTheme.JabRefIcons.ADD.asToggleButton();
    EasyBind.subscribe(addToggle.selectedProperty(),selected -> {
      if (selected) {
        addToggle.setGraphic(IconTheme.JabRefIcons.ADD_FILLED.withColor(IconTheme.SELECTED_COLOR).getGraphicNode());
      }
 else {
        addToggle.setGraphic(IconTheme.JabRefIcons.ADD.getGraphicNode());
      }
    }
);
    addToggle.getStyleClass().add("addEntryButton");
    addToggle.selectedProperty().bindBidirectional(entriesListView.getItemBooleanProperty(entry));
    HBox separator=new HBox();
    HBox.setHgrow(separator,Priority.SOMETIMES);
    Node entryNode=getEntryNode(entry);
    HBox.setHgrow(entryNode,Priority.ALWAYS);
    HBox container=new HBox(entryNode,separator,addToggle);
    container.getStyleClass().add("entry-container");
    BindingsHelper.includePseudoClassWhen(container,entrySelected,addToggle.selectedProperty());
    BackgroundTask.wrap(() -> viewModel.hasDuplicate(entry)).onSuccess(duplicateFound -> {
      if (duplicateFound) {
        Button duplicateButton=IconTheme.JabRefIcons.DUPLICATE.asButton();
        duplicateButton.setTooltip(new Tooltip(Localization.lang("Possible duplicate of existing entry. Click to resolve.")));
        duplicateButton.setOnAction(event -> viewModel.resolveDuplicate(entry));
        container.getChildren().add(1,duplicateButton);
      }
    }
).executeWith(Globals.TASK_EXECUTOR);
    return container;
  }
).withOnMouseClickedEvent((entry,event) -> entriesListView.getCheckModel().toggleCheckState(entry)).withPseudoClass(entrySelected,entriesListView::getItemBooleanProperty).install(entriesListView);
  entriesListView.setSelectionModel(new NoSelectionModel<>());
}
