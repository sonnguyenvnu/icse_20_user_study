/** 
 * Initializes the components, the layout, the data structure and the actions in this dialog.
 */
private void initialize(){
  Button buttonBrowse=new Button(Localization.lang("Browse"));
  buttonBrowse.setTooltip(new Tooltip(Localization.lang("Opens the file browser.")));
  buttonBrowse.getStyleClass().add("text-button");
  buttonBrowse.setOnAction(e -> {
    DirectoryDialogConfiguration directoryDialogConfiguration=new DirectoryDialogConfiguration.Builder().withInitialDirectory(preferences.get(JabRefPreferences.WORKING_DIRECTORY)).build();
    dialogService.showDirectorySelectionDialog(directoryDialogConfiguration).ifPresent(selectedDirectory -> {
      textfieldDirectoryPath.setText(selectedDirectory.toAbsolutePath().toString());
      preferences.put(JabRefPreferences.WORKING_DIRECTORY,selectedDirectory.toAbsolutePath().toString());
    }
);
  }
);
  buttonScan=new Button(Localization.lang("Scan directory"));
  buttonScan.setTooltip(new Tooltip((Localization.lang("Searches the selected directory for unlinked files."))));
  buttonScan.setOnAction(e -> startSearch());
  buttonScan.setDefaultButton(true);
  buttonScan.setPadding(new Insets(5,0,0,0));
  buttonExport=new Button(Localization.lang("Export selected entries"));
  buttonExport.setTooltip(new Tooltip(Localization.lang("Export to text file.")));
  buttonExport.getStyleClass().add("text-button");
  buttonExport.setDisable(true);
  buttonExport.setOnAction(e -> startExport());
  ButtonType buttonTypeImport=new ButtonType(Localization.lang("Import"),ButtonBar.ButtonData.OK_DONE);
  getDialogPane().getButtonTypes().setAll(buttonTypeImport,ButtonType.CANCEL);
  buttonApply=(Button)getDialogPane().lookupButton(buttonTypeImport);
  buttonApply.setTooltip(new Tooltip((Localization.lang("Starts the import of BibTeX entries."))));
  buttonApply.setDisable(true);
  Button buttonOptionSelectAll=new Button();
  buttonOptionSelectAll.setText(Localization.lang("Select all"));
  buttonOptionSelectAll.getStyleClass().add("text-button");
  buttonOptionSelectAll.setOnAction(event -> {
    CheckBoxTreeItem<FileNodeWrapper> root=(CheckBoxTreeItem<FileNodeWrapper>)tree.getRoot();
    root.setSelected(true);
    root.setSelected(false);
    root.setSelected(true);
  }
);
  Button buttonOptionDeselectAll=new Button();
  buttonOptionDeselectAll.setText(Localization.lang("Unselect all"));
  buttonOptionDeselectAll.getStyleClass().add("text-button");
  buttonOptionDeselectAll.setOnAction(event -> {
    CheckBoxTreeItem<FileNodeWrapper> root=(CheckBoxTreeItem<FileNodeWrapper>)tree.getRoot();
    root.setSelected(false);
    root.setSelected(true);
    root.setSelected(false);
  }
);
  Button buttonOptionExpandAll=new Button();
  buttonOptionExpandAll.setText(Localization.lang("Expand all"));
  buttonOptionExpandAll.getStyleClass().add("text-button");
  buttonOptionExpandAll.setOnAction(event -> {
    CheckBoxTreeItem<FileNodeWrapper> root=(CheckBoxTreeItem<FileNodeWrapper>)tree.getRoot();
    expandTree(root,true);
  }
);
  Button buttonOptionCollapseAll=new Button();
  buttonOptionCollapseAll.setText(Localization.lang("Collapse all"));
  buttonOptionCollapseAll.getStyleClass().add("text-button");
  buttonOptionCollapseAll.setOnAction(event -> {
    CheckBoxTreeItem<FileNodeWrapper> root=(CheckBoxTreeItem<FileNodeWrapper>)tree.getRoot();
    expandTree(root,false);
    root.setExpanded(true);
  }
);
  textfieldDirectoryPath=new TextField();
  Path initialPath=databaseContext.getFirstExistingFileDir(preferences.getFilePreferences()).orElse(preferences.getWorkingDir());
  textfieldDirectoryPath.setText(initialPath.toAbsolutePath().toString());
  Label labelDirectoryDescription=new Label(Localization.lang("Select a directory where the search shall start."));
  Label labelFileTypesDescription=new Label(Localization.lang("Select file type:"));
  Label labelFilesDescription=new Label(Localization.lang("These files are not linked in the active library."));
  Label labelSearchingDirectoryInfo=new Label(Localization.lang("Searching file system..."));
  tree=new TreeView<>();
  tree.setPrefWidth(Double.POSITIVE_INFINITY);
  ScrollPane scrollPaneTree=new ScrollPane(tree);
  scrollPaneTree.setFitToWidth(true);
  ProgressIndicator progressBarSearching=new ProgressIndicator();
  progressBarSearching.setMaxSize(50,50);
  setResultConverter(buttonPressed -> {
    if (buttonPressed == buttonTypeImport) {
      startImport();
    }
 else {
      if (findUnlinkedFilesTask != null) {
        findUnlinkedFilesTask.cancel();
      }
    }
    return null;
  }
);
  new ViewModelTreeCellFactory<FileNodeWrapper>().withText(node -> {
    if (Files.isRegularFile(node.path)) {
      return node.path.getFileName().toString();
    }
 else {
      return node.path.getFileName() + " (" + node.fileCount + " file" + (node.fileCount > 1 ? "s" : "") + ")";
    }
  }
).install(tree);
  List<FileChooser.ExtensionFilter> fileFilterList=Arrays.asList(FileFilterConverter.ANY_FILE,FileFilterConverter.toExtensionFilter(StandardFileType.PDF),FileFilterConverter.toExtensionFilter(StandardFileType.BIBTEX_DB));
  comboBoxFileTypeSelection=new ComboBox<>(FXCollections.observableArrayList(fileFilterList));
  comboBoxFileTypeSelection.getSelectionModel().selectFirst();
  new ViewModelListCellFactory<FileChooser.ExtensionFilter>().withText(fileFilter -> fileFilter.getDescription() + fileFilter.getExtensions().stream().collect(Collectors.joining(", "," (",")"))).withIcon(fileFilter -> ExternalFileTypes.getInstance().getExternalFileTypeByExt(fileFilter.getExtensions().get(0)).map(ExternalFileType::getIcon).orElse(null)).install(comboBoxFileTypeSelection);
  panelSearchProgress=new VBox(5,labelSearchingDirectoryInfo,progressBarSearching);
  panelSearchProgress.toFront();
  panelSearchProgress.setVisible(false);
  VBox panelDirectory=new VBox(5);
  panelDirectory.getChildren().setAll(labelDirectoryDescription,new HBox(10,textfieldDirectoryPath,buttonBrowse),new HBox(15,labelFileTypesDescription,comboBoxFileTypeSelection),buttonScan);
  HBox.setHgrow(textfieldDirectoryPath,Priority.ALWAYS);
  StackPane stackPaneTree=new StackPane(scrollPaneTree,panelSearchProgress);
  StackPane.setAlignment(panelSearchProgress,Pos.CENTER);
  BorderPane panelFiles=new BorderPane();
  panelFiles.setTop(labelFilesDescription);
  panelFiles.setCenter(stackPaneTree);
  panelFiles.setBottom(new HBox(5,buttonOptionSelectAll,buttonOptionDeselectAll,buttonOptionExpandAll,buttonOptionCollapseAll,buttonExport));
  VBox container=new VBox(20);
  container.getChildren().addAll(panelDirectory,panelFiles);
  container.setPrefWidth(600);
  getDialogPane().setContent(container);
}
