/*
 * Copyright 2003-2019 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.ide.findusages.view;

import com.intellij.icons.AllIcons.Actions;
import com.intellij.icons.AllIcons.General;
import com.intellij.icons.AllIcons.Toolwindows;
import com.intellij.ide.actions.PinActiveTabAction;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.progress.PerformInBackgroundOption;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task.Backgroundable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import jetbrains.mps.ide.ThreadUtils;
import jetbrains.mps.ide.actions.MPSActions;
import jetbrains.mps.ide.actions.MPSCommonDataKeys;
import jetbrains.mps.ide.findusages.CantLoadSomethingException;
import jetbrains.mps.ide.findusages.CantSaveSomethingException;
import jetbrains.mps.ide.findusages.model.IResultProvider;
import jetbrains.mps.ide.findusages.model.SearchQuery;
import jetbrains.mps.ide.findusages.model.SearchResult;
import jetbrains.mps.ide.findusages.model.SearchResults;
import jetbrains.mps.ide.findusages.view.UsagesView.RebuildAction;
import jetbrains.mps.ide.findusages.view.UsagesView.RerunAction;
import jetbrains.mps.ide.findusages.view.UsagesView.SearchTaskImpl;
import jetbrains.mps.ide.findusages.view.treeholder.tree.DataTreeChangesNotifier;
import jetbrains.mps.ide.findusages.view.treeholder.treeview.INodeRepresentator;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.openapi.navigation.EditorNavigator;
import jetbrains.mps.progress.ProgressMonitorAdapter;
import jetbrains.mps.smodel.RepoListenerRegistrar;
import jetbrains.mps.util.annotation.ToRemove;
import org.apache.log4j.Logger;
import org.jdom.Element;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.module.SRepository;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.text.html.HTMLDocument.RunElement;
import java.util.ArrayList;
import java.util.List;

@State(
    name = "UsagesViewTool",
    storages = @Storage(StoragePathMacros.WORKSPACE_FILE)
)
public class UsagesViewTool extends TabbedUsagesTool implements PersistentStateComponent<Element> {

  private static final String VERSION_NUMBER = "1";
  private static final String VERSION = "version";
  private static final String ID = "id";

  private static final String TAB = "tab";
  private static final String TABS = "tabs";

  private static final String DEFAULT_VIEW_OPTIONS = "default_view_options";
  private static final String TOOL_WINDOW_ID = "Usages";

  private List<UsageViewData> myUsageViewsData = new ArrayList<>();
  private jetbrains.mps.ide.findusages.view.treeholder.treeview.ViewOptions myDefaultViewOptions =
      new jetbrains.mps.ide.findusages.view.treeholder.treeview.ViewOptions();
  private final DataTreeChangesNotifier myChangeTracker = new DataTreeChangesNotifier();

  //----CONSTRUCT STUFF----

  public UsagesViewTool(Project project) {
    super(project, TOOL_WINDOW_ID, 3, Toolwindows.ToolWindowFind, ToolWindowAnchor.BOTTOM, true);
  }

  @Override
  protected UsagesView getUsagesView(int index) {
    return myUsageViewsData.get(index).myUsagesView;
  }

  private void register(UsageViewData viewData) {
    if (myUsageViewsData.isEmpty()) {
      new RepoListenerRegistrar(ProjectHelper.getProjectRepository(getProject()), myChangeTracker).attach();
    }
    myUsageViewsData.add(viewData);
  }

  @Override
  protected void onRemove(int index) {
    myUsageViewsData.remove(index);
    if (myUsageViewsData.isEmpty()) {
      new RepoListenerRegistrar(ProjectHelper.getProjectRepository(getProject()), myChangeTracker).detach();
    }
  }

  //----TOOL STUFF----

  public int getPriority() {
    return 0;
  }

  @Override
  protected boolean isInitiallyAvailable() {
    return true;
  }

  //---FIND USAGES STUFF----

  /**
   * Display usages in a tool window of a respective project, according to options supplied.
   */
  public static void showUsages(@NotNull Project project, @NotNull IResultProvider provider, @NotNull SearchQuery query, @NotNull UsageToolOptions options) {
    project.getComponent(UsagesViewTool.class).findUsages(provider, query, options);
  }

  /**
   * @deprecated Use {@link #showUsages(com.intellij.openapi.project.Project, jetbrains.mps.ide.findusages.model.IResultProvider, jetbrains.mps.ide.findusages.model.SearchQuery, UsageToolOptions)} instead
   */
  @Deprecated
  @ToRemove(version = 3.3)
  public void findUsages(IResultProvider provider, SearchQuery query, boolean isRerunnable, boolean showOne, boolean forceNewTab, String notFoundMsg) {
    findUsages(provider, query,
               new UsageToolOptions().allowRunAgain(isRerunnable).navigateIfSingle(!showOne).forceNewTab(forceNewTab).notFoundMessage(notFoundMsg));
  }

  private void findUsages(IResultProvider provider, final SearchQuery query, final UsageToolOptions options) {
    final SearchTaskImpl searchTask = new SearchTaskImpl(ProjectHelper.fromIdeaProject(getProject()), provider, query);
    ThreadUtils.runInUIThreadNoWait(() -> new Backgroundable(getProject(), "Searching", true, PerformInBackgroundOption.DEAF) {
      private SearchResults searchResults;

      @Override
      public void run(@NotNull final ProgressIndicator indicator) {
        searchResults = searchTask.execute(new ProgressMonitorAdapter(indicator));
      }

      @Override
      public void onSuccess() {
        showResults(searchTask, searchResults, options, null);
      }
    }.queue());
  }

  public void show(SearchResults results, String notFoundMsg) {
    ThreadUtils.assertEDT();
    showResults(null, results, new UsageToolOptions().navigateIfSingle(false).forceNewTab(true).allowRunAgain(false).notFoundMessage(notFoundMsg), null);
  }

  public void show(SearchResults results, String notFoundMsg, @Nullable INodeRepresentator representator) {
    ThreadUtils.assertEDT();
    showResults(null, results, new UsageToolOptions().navigateIfSingle(false).forceNewTab(true).allowRunAgain(false).notFoundMessage(notFoundMsg), representator);
  }

  @Nullable
  private void showResults(SearchTaskImpl searchTask, final SearchResults<?> searchResults, UsageToolOptions options, @Nullable INodeRepresentator representator) {
    final jetbrains.mps.project.Project mpsProject = ProjectHelper.toMPSProject(getProject());
    int resCount = searchResults.getSearchResults().size();
    if (resCount == 0) {
      final ToolWindowManager manager = ToolWindowManager.getInstance(getProject());
      manager.notifyByBalloon(TOOL_WINDOW_ID, MessageType.INFO, options.myNotFoundMessage, null, null);
      return;
    } else if (resCount == 1 && options.myNavigateIfSingle) {
      final SearchResult<?> searchResult = searchResults.getSearchResults().get(0);
      if (searchResult.getObject() instanceof SNode) {
        final SNode node = (SNode) searchResult.getObject();
        new EditorNavigator(mpsProject).shallFocus(true).selectIfChild().open(node.getReference());
        return;
      }
      // FALL THROUGH (single result we can't navigate to)
    }
    int index = getCurrentTabIndex();
    UsagesView usagesView = createUsageView(options.myRunAgain ? searchTask : null);
    usagesView.setCustomNodeRepresentator(representator);
    UsageViewData usageViewData = new UsageViewData(usagesView, options.myRunAgain ? searchTask : null);
    usageViewData.setTransientView(options.myTransientView);
    register(usageViewData);

    usagesView.setContents(searchResults);

    Icon icon = usagesView.getIcon();
    String caption = usagesView.getCaption();
    JComponent component = usagesView.getComponent();
    Content content = addContent(component, caption, icon, true);
    getContentManager().setSelectedContent(content);

    if (!options.myForceNewTab) {
      closeLastUnpinnedTab(index);
    }
    openTool(true);
  }

  //---END FIND STUFF----

  private void read(Element element, jetbrains.mps.project.Project project) {
    Element versionXML = element.getChild(VERSION);
    if (versionXML == null) {
      return;
    }
    String version = versionXML.getAttribute(ID).getValue();
    if (!VERSION_NUMBER.equals(version)) {
      return;
    }

    Element tabsXML = element.getChild(TABS);
    if (tabsXML != null) {
      for (Element tabXML : tabsXML.getChildren()) {
        final UsageViewData usageViewData;
        try {
          usageViewData = UsageViewData.read(this, tabXML, project);
        } catch (RuntimeException ex) {
          Logger.getLogger(UsagesViewTool.class).info("Failed to restore usages view tab", ex);
          continue;
        } catch (CantLoadSomethingException e) {
          continue;
        }
        register(usageViewData);

        ApplicationManager.getApplication().invokeLater(() -> {
          final String caption = usageViewData.myUsagesView.getCaption();
          final Icon icon = usageViewData.myUsagesView.getIcon();
          addContent(usageViewData.myUsagesView.getComponent(), caption, icon, true);
        });
      }
    }

    Element defaultViewOptionsXML = element.getChild(DEFAULT_VIEW_OPTIONS);
    myDefaultViewOptions.read(defaultViewOptionsXML, project);

    ApplicationManager.getApplication().invokeLater(() -> {
      ContentManager cm = getContentManager();
      if (cm == null) {
        return;
      }
      if (cm.getContentCount() == 0) {
        makeUnavailableLater();
      }
    });
  }

  private void write(Element element, jetbrains.mps.project.Project project) {
    Element versionXML = new Element(VERSION);
    versionXML.setAttribute(ID, VERSION_NUMBER);
    element.addContent(versionXML);

    Element tabsXML = new Element(TABS);
    for (UsageViewData usageViewData : myUsageViewsData) {
      if (usageViewData.isTransientView()) {
        continue;
      }
      try {
        Element tabXML = new Element(TAB);
        usageViewData.write(tabXML, project);
        tabsXML.addContent(tabXML);
      } catch (CantSaveSomethingException e) {
        // ignore
      }
    }
    element.addContent(tabsXML);

    Element defaultViewOptionsXML = new Element(DEFAULT_VIEW_OPTIONS);
    myDefaultViewOptions.write(defaultViewOptionsXML, project);
    element.addContent(defaultViewOptionsXML);
  }

  @Override
  public Element getState() {
    final jetbrains.mps.project.Project mpsProject = ProjectHelper.toMPSProject(getProject());
    final Element state = new Element("state");
    mpsProject.getModelAccess().runReadAction(() -> write(state, mpsProject));
    return state;
  }

  @Override
  public void loadState(@NotNull final Element state) {
    //startup manager is needed cause the contract is that you can't use read and write locks
    //on component load - it can cause a deadlock (MPS-2811) 
    StartupManager.getInstance(getProject()).runWhenProjectIsInitialized(() -> {
      if (getProject().isDisposed()) {
        return;
      }
      final jetbrains.mps.project.Project mpsProject = ProjectHelper.toMPSProject(getProject());
      mpsProject.getModelAccess().runReadAction(() -> read(state, mpsProject));
    });
  }

  private UsagesView createUsageView(@Nullable SearchTaskImpl searchTask) {
    jetbrains.mps.project.Project mpsProject = ProjectHelper.fromIdeaProject(getProject());
    final UsagesView view = new UsagesView(mpsProject, myDefaultViewOptions, myChangeTracker);
    ArrayList<AnAction> actions = new ArrayList<>();
    if (searchTask != null) {
      final RerunAction rerunAction = new RerunAction(view, "Run again");
      rerunAction.setRunOptions(searchTask);
      actions.add(rerunAction);
    }
    actions.add(new RebuildAction(view));
    actions.add(new AnAction("Close", "", Actions.Cancel) {
      @Override
      public void actionPerformed(@NotNull AnActionEvent e) {
        int i = 0;
        for (UsageViewData vd : myUsageViewsData) {
          if (vd.myUsagesView == view) {
            UsagesViewTool.this.closeTab(i);
            break;
          }
          i++;
        }
      }
    });
    actions.add(new PinActiveTabAction.TW());
    if (ActionManager.getInstance().getAction(MPSActions.FIND_USAGES_WITH_DIALOG_ACTION) != null && searchTask != null) {
      actions.add(new FindUsagesWithDialogAction(mpsProject.getRepository(), searchTask));
    }
    view.setActions(actions);
    return view;
  }

  /**
   * Tracks result presentation and optional task to re-populate the view.
   * Persists state
   */
  private static class UsageViewData {
    private static final String USAGE_VIEW = "usage_view";
    private static final String USAGE_VIEW_OPTIONS = "usage_view_options";

    public final UsagesView myUsagesView;
    public final SearchTaskImpl mySearchTask;
    private boolean myIsTransientView = false;
    // now it's not in use, but will be used to implement constructable finders
//    private FindUsagesOptions myOptions = new FindUsagesOptions();

    public UsageViewData(@NotNull UsagesView view, @Nullable SearchTaskImpl searchTask) {
      myUsagesView = view;
      mySearchTask = searchTask;
    }

    /*package*/void setTransientView(boolean isTransientView) {
      myIsTransientView = isTransientView;
    }

    /*package*/boolean isTransientView() {
      return myIsTransientView;
    }

    @NotNull
    public static UsageViewData read(UsagesViewTool tool, Element element, jetbrains.mps.project.Project project) throws CantLoadSomethingException {
      final SearchTaskImpl task = SearchTaskImpl.read(element, project);
      final UsagesView usageView = tool.createUsageView(task);
      Element usageViewXML = element.getChild(USAGE_VIEW);
      usageView.read(usageViewXML, project);

//      Element usageViewOptionsXML = element.getChild(USAGE_VIEW_OPTIONS);
//      myOptions = new FindUsagesOptions(usageViewOptionsXML, project);
      return new UsageViewData(usageView, task);
    }

    public void write(Element element, jetbrains.mps.project.Project project) throws CantSaveSomethingException {
      //this is to partially fix MPS-14671
      if (myUsagesView.getIncludedResultNodes().size() > 500) {
        throw new CantSaveSomethingException("usages view size too big to save");
      }


      if (mySearchTask != null) {
        mySearchTask.write(element, project);
      }
      Element usageViewXML = new Element(USAGE_VIEW);
      myUsagesView.write(usageViewXML, project);
      element.addContent(usageViewXML);

//      Element usageViewOptionsXML = new Element(USAGE_VIEW_OPTIONS);
//      myOptions.write(usageViewOptionsXML, project);
//      element.addContent(usageViewOptionsXML);
    }
  }

  private static class FindUsagesWithDialogAction extends AnAction {
    private final SRepository myRepository;
    private final SearchTaskImpl mySearchTask;

    public FindUsagesWithDialogAction(@NotNull SRepository repository, @NotNull SearchTaskImpl searchTask) {
      super("Settings...", "Show find usages settings dialog", General.ProjectSettings);
      myRepository = repository;
      mySearchTask = searchTask;
    }

    @Override
    public void update(AnActionEvent e) {
      e.getPresentation().setEnabled(ActionManager.getInstance().getAction(MPSActions.FIND_USAGES_WITH_DIALOG_ACTION) != null);
    }

    @Override
    public void actionPerformed(final AnActionEvent e) {
      if (!mySearchTask.canExecute()) {
        return;
      }
      if (!(mySearchTask.getSearchObject() instanceof SNodeReference)) {
        return; //object of an incompatible kind (see #getData() below)
      }
      final SNodeReference searchedNode = (SNodeReference) mySearchTask.getSearchObject();

      DataContext dataContext = new DataContext() {
        private final DataContext myDelegate = e.getDataContext();

        @Nullable
        @Override
        public Object getData(@NonNls String dataId) {
          if (MPSCommonDataKeys.CONTEXT_MODEL.is(dataId)) {
            SNode resolved = searchedNode.resolve(myRepository);
            return resolved == null ? null : resolved.getModel();
          }
          // if a caller asks for an SNode, I assume it has appropriate model read, otherwise what would be SNode for?
          if (MPSCommonDataKeys.NODE.is(dataId)) {
            return searchedNode.resolve(myRepository);
          }
          return myDelegate.getData(dataId);
        }
      };
      AnActionEvent event = new AnActionEvent(e.getInputEvent(), dataContext, e.getPlace(), e.getPresentation(), e.getActionManager(), e.getModifiers());

      AnAction action = ActionManager.getInstance().getAction(MPSActions.FIND_USAGES_WITH_DIALOG_ACTION);
      action.actionPerformed(event);
    }
  }
}
