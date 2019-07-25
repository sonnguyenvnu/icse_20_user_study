public void initialize(){
  viewModel=new FileTabViewModel(dialogService,preferences);
  openLastStartup.selectedProperty().bindBidirectional(viewModel.openLastStartupProperty());
  backupOldFile.selectedProperty().bindBidirectional(viewModel.backupOldFileProperty());
  noWrapFiles.textProperty().bindBidirectional(viewModel.noWrapFilesProperty());
  resolveStringsBibTex.selectedProperty().bindBidirectional(viewModel.resolveStringsBibTexProperty());
  resolveStringsAll.selectedProperty().bindBidirectional(viewModel.resolveStringsAllProperty());
  resolveStringsExcept.textProperty().bindBidirectional(viewModel.resolvStringsExceptProperty());
  newLineSeparator.itemsProperty().bind(viewModel.newLineSeparatorListProperty());
  newLineSeparator.valueProperty().bindBidirectional(viewModel.selectedNewLineSeparatorProperty());
  alwaysReformatBib.selectedProperty().bindBidirectional(viewModel.alwaysReformatBibProperty());
  mainFileDir.textProperty().bindBidirectional(viewModel.mainFileDirProperty());
  useBibLocationAsPrimary.selectedProperty().bindBidirectional(viewModel.useBibLocationAsPrimaryProperty());
  autolinkFileStartsBibtex.selectedProperty().bindBidirectional(viewModel.autolinkFileStartsBibtexProperty());
  autolinkFileExactBibtex.selectedProperty().bindBidirectional(viewModel.autolinkFileExactBibtexProperty());
  autolinkUseRegex.selectedProperty().bindBidirectional(viewModel.autolinkUseRegexProperty());
  autolinkRegexKey.textProperty().bindBidirectional(viewModel.autolinkRegexKeyProperty());
  searchFilesOnOpen.selectedProperty().bindBidirectional(viewModel.searchFilesOnOpenProperty());
  openBrowseOnCreate.selectedProperty().bindBidirectional(viewModel.openBrowseOnCreateProperty());
  autosaveLocalLibraries.selectedProperty().bindBidirectional(viewModel.autosaveLocalLibrariesProperty());
  ActionFactory actionFactory=new ActionFactory(Globals.getKeyPrefs());
  actionFactory.configureIconButton(StandardActions.HELP_REGEX_SEARCH,new HelpAction(HelpFile.REGEX_SEARCH),autolinkRegexHelp);
  actionFactory.configureIconButton(StandardActions.HELP,new HelpAction(HelpFile.AUTOSAVE),autosaveLocalLibrariesHelp);
  validationVisualizer.setDecoration(new IconValidationDecorator());
  Platform.runLater(() -> validationVisualizer.initVisualization(viewModel.mainFileDirValidationStatus(),mainFileDir));
}
