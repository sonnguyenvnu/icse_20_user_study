/*
 * Copyright 2003-2018 JetBrains s.r.o.
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
package jetbrains.mps.nodeEditor;

import com.intellij.ide.BrowserUtil;
import com.intellij.ide.CopyProvider;
import com.intellij.ide.CutProvider;
import com.intellij.ide.DataManager;
import com.intellij.ide.PasteProvider;
import com.intellij.ide.SelectInContext;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.KeyboardShortcut;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.actionSystem.Separator;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.colors.EditorColors;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.impl.LeftHandScrollbarLayout;
import com.intellij.openapi.keymap.KeymapManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.wm.IdeGlassPane;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.ex.StatusBarEx;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.components.JBScrollBar;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.io.URLUtil;
import com.intellij.util.ui.ButtonlessScrollBarUI;
import com.intellij.util.ui.UIUtil;
import jetbrains.mps.RuntimeFlags;
import jetbrains.mps.classloading.ClassLoaderManager;
import jetbrains.mps.classloading.DeployListener;
import jetbrains.mps.editor.runtime.cells.ReadOnlyUtil;
import jetbrains.mps.editor.runtime.commands.EditorCommand;
import jetbrains.mps.editor.runtime.commands.EditorCommandAdapter;
import jetbrains.mps.editor.runtime.style.StyleAttributes;
import jetbrains.mps.errors.item.IssueKindReportItem;
import jetbrains.mps.ide.MPSCoreComponents;
import jetbrains.mps.ide.ThreadUtils;
import jetbrains.mps.ide.actions.MPSActions;
import jetbrains.mps.ide.actions.MPSCommonDataKeys;
import jetbrains.mps.ide.editor.MPSEditorDataKeys;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.ide.projectView.ProjectViewSelectInProvider;
import jetbrains.mps.ide.tooltips.MPSToolTipManager;
import jetbrains.mps.ide.tooltips.TooltipComponent;
import jetbrains.mps.lang.smodel.generator.smodelAdapter.AttributeOperations;
import jetbrains.mps.logging.Logger;
import jetbrains.mps.module.ReloadableModule;
import jetbrains.mps.nodeEditor.actions.ActionHandlerImpl;
import jetbrains.mps.nodeEditor.assist.DefaultContextAssistantManager;
import jetbrains.mps.nodeEditor.assist.DisabledContextAssistantManager;
import jetbrains.mps.nodeEditor.cellMenu.NodeSubstituteChooser;
import jetbrains.mps.nodeEditor.cellMenu.NodeSubstituteChooserHandler;
import jetbrains.mps.nodeEditor.cellMenu.NodeSubstitutePatternEditor;
import jetbrains.mps.nodeEditor.cells.APICellAdapter;
import jetbrains.mps.nodeEditor.cells.CellFinderUtil;
import jetbrains.mps.nodeEditor.cells.CellFinderUtil.Finder;
import jetbrains.mps.nodeEditor.cells.EditorCell;
import jetbrains.mps.nodeEditor.cells.EditorCell_Basic;
import jetbrains.mps.nodeEditor.cells.EditorCell_Collection;
import jetbrains.mps.nodeEditor.cells.EditorCell_Constant;
import jetbrains.mps.nodeEditor.cells.EditorCell_Label;
import jetbrains.mps.nodeEditor.cells.EditorCell_Property;
import jetbrains.mps.nodeEditor.commands.CommandContextImpl;
import jetbrains.mps.nodeEditor.commands.CommandContextWrapper;
import jetbrains.mps.nodeEditor.configuration.EditorConfiguration;
import jetbrains.mps.nodeEditor.configuration.EditorConfigurationBuilder;
import jetbrains.mps.nodeEditor.deletionApprover.DeletionApproverImpl;
import jetbrains.mps.nodeEditor.highlighter.EditorComponentCreateListener;
import jetbrains.mps.nodeEditor.highlighter.EditorHighlighter;
import jetbrains.mps.nodeEditor.keymaps.AWTKeymapHandler;
import jetbrains.mps.nodeEditor.keymaps.KeymapHandler;
import jetbrains.mps.nodeEditor.leftHighlighter.LeftEditorHighlighter;
import jetbrains.mps.nodeEditor.selection.SelectionInternal;
import jetbrains.mps.nodeEditor.selection.SelectionManagerImpl;
import jetbrains.mps.nodeEditor.sidetransform.EditorCell_STHint;
import jetbrains.mps.nodeEditor.ui.InputMethodRequestsImpl;
import jetbrains.mps.nodeEditor.updater.UpdaterImpl;
import jetbrains.mps.nodefs.MPSNodeVirtualFile;
import jetbrains.mps.openapi.editor.ActionHandler;
import jetbrains.mps.openapi.editor.DeletionApprover;
import jetbrains.mps.openapi.editor.assist.ContextAssistant;
import jetbrains.mps.openapi.editor.assist.ContextAssistantManager;
import jetbrains.mps.openapi.editor.cells.CellAction;
import jetbrains.mps.openapi.editor.cells.CellActionType;
import jetbrains.mps.openapi.editor.cells.CellMessagesUtil;
import jetbrains.mps.openapi.editor.cells.CellTraversalUtil;
import jetbrains.mps.openapi.editor.cells.EditorCellContext;
import jetbrains.mps.openapi.editor.cells.KeyMapAction;
import jetbrains.mps.openapi.editor.cells.SubstituteInfo;
import jetbrains.mps.openapi.editor.commands.CommandContext;
import jetbrains.mps.openapi.editor.message.EditorMessageOwner;
import jetbrains.mps.openapi.editor.message.SimpleEditorMessage;
import jetbrains.mps.openapi.editor.selection.Selection;
import jetbrains.mps.openapi.editor.selection.SelectionManager;
import jetbrains.mps.openapi.editor.selection.SingularSelection;
import jetbrains.mps.openapi.editor.style.StyleRegistry;
import jetbrains.mps.openapi.editor.update.Updater;
import jetbrains.mps.project.MPSProject;
import jetbrains.mps.smodel.CancellableReadAction;
import jetbrains.mps.typechecking.TypecheckingFacade;
import jetbrains.mps.typechecking.backend.TypecheckingSession;
import jetbrains.mps.typesystem.inference.DefaultTypecheckingContextOwner;
import jetbrains.mps.typesystem.inference.ITypeContextOwner;
import jetbrains.mps.typesystem.inference.TypeCheckingContext;
import jetbrains.mps.typesystem.inference.TypeContextManager;
import jetbrains.mps.typesystem.inference.util.ConcurrentSubtypingCache;
import jetbrains.mps.typesystem.inference.util.SubtypingCache;
import jetbrains.mps.util.Computable;
import jetbrains.mps.util.ComputeRunnable;
import jetbrains.mps.util.Pair;
import jetbrains.mps.util.Reference;
import jetbrains.mps.util.annotation.ToRemove;
import jetbrains.mps.workbench.ActionPlace;
import jetbrains.mps.workbench.action.ActionUtils;
import jetbrains.mps.workbench.action.BaseAction;
import org.apache.log4j.LogManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.model.SNodeUtil;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.util.ProgressMonitor;
import org.jetbrains.mps.util.Condition;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.DefaultEditorKit;
import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.im.InputMethodRequests;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.Collectors;

public abstract class EditorComponent extends JComponent implements Scrollable, DataProvider, ITypeContextOwner, TooltipComponent,
                                                                    jetbrains.mps.openapi.editor.EditorComponent {

  private static final Logger LOG = Logger.wrap(LogManager.getLogger(EditorComponent.class));
  public static final String EDITOR_POPUP_MENU_ACTIONS = MPSActions.EDITOR_POPUP_GROUP;

  private static final int SCROLL_GAP = 15;
  private ClassLoaderManager myClassLoaderManager = null;

  private String myDefaultPopupGroupId = MPSActions.EDITOR_POPUP_GROUP;
  private InputMethodRequests myInputMethodRequests;
  protected TypecheckingSession myTypecheckingSession;

  public static void turnOnAliasingIfPossible(Graphics2D g) {
    if (EditorSettings.getInstance().isUseAntialiasing()) {
      Toolkit tk = Toolkit.getDefaultToolkit();
      //noinspection HardCodedStringLiteral
      Map map = (Map) tk.getDesktopProperty("awt.font.desktophints");
      if (map != null) {
        g.addRenderingHints(map);
      } else {
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      }
      g.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, UIUtil.getLcdContrastValue());
    }
  }

  private final Object myAdditionalPaintersLock = new Object();

  private Map<jetbrains.mps.openapi.editor.cells.EditorCell, Boolean> myCollapseStates = new HashMap<>();
  private Set<EditorCell> myBracesEnabledCells = new HashSet<>();

  private CellTracker myCellTracker = new CellTracker();

  private boolean myDisposed = false;
  // additional debugging field
  private StackTraceElement[] myModelDisposedStackTrace = null;
  private Throwable myDisposedTrace = null;

  private DeletionApproverImpl myDeletionApprover;

  private Set<AdditionalPainter> myAdditionalPainters = new TreeSet<>((o1, o2) -> {
    if (o1.isAbove(o2, EditorComponent.this)) {
      return 1;
    }
    if (o2.isAbove(o1, EditorComponent.this)) {
      return -1;
    }
    return o1.equals(o2) ? 0 : Integer.signum(System.identityHashCode(o1) - System.identityHashCode(o2));
  });
  private Map<Object, AdditionalPainter> myItemsToAdditionalPainters = new HashMap<>();

  private final List<LeftMarginMouseListener> myLeftMarginPressListeners = new ArrayList<>(0);

  private EditorSettingsListener mySettingsListener = () -> getModelAccess().runReadInEDT(() -> {
    if (isDisposed()) {
      return;
    }
    rebuildEditorContent();
  });

  private final DeployListener myClassesListener = new DeployListener() {
    @Override
    public void onUnloaded(@NotNull Set<ReloadableModule> unloadedModules, @NotNull ProgressMonitor monitor) {
    }

    @Override
    public void onLoaded(@NotNull Set<ReloadableModule> loadedModules, @NotNull ProgressMonitor monitor) {
      getModelAccess().runReadInEDT(() -> {
        if (isDisposed() || isModuleDisposed() || isProjectDisposed() || isNodeDisposed()) {
          return;
        }
        rebuildEditorContent();
        myNodeSubstituteChooser.clearContent();
      });
    }
  };

  private boolean myReadOnly = false;
  private String myLastWrittenStatus = "";

  @NotNull
  private final SRepository myRepository;

  //TODO: make @NotNull after separating UI-less logic into AbstractEditorComponent class
  private JScrollPane myScrollPane;
  //TODO: make @NotNull after separating UI-less logic into AbstractEditorComponent class
  private MyScrollBar myVerticalScrollBar;
  //TODO: make @NotNull after separating UI-less logic into AbstractEditorComponent class
  private JComponent myContainer;

  protected EditorCell myRootCell;
  private int myShiftX = 15;
  private int myShiftY = 10;

  private SelectionManagerImpl mySelectionManager = new SelectionManagerImpl(this);
  @NotNull
  private final CommandContextImpl myCommandContext;
  private final UpdaterImpl myUpdater;

  private Stack<KeyboardHandler> myKbdHandlersStack;
  private MouseListener myMouseEventHandler;

  private final Object myEditorComponentActionsLock = new Object();
  private volatile EditorComponentActions myEditorComponentActions;

  private NodeSubstituteChooser myNodeSubstituteChooser;
  private NodeInformationDialog myNodeInformationDialog;

  private List<EditorDisposeListener> myDisposeListeners = new ArrayList<>();
  private final NodeHighlightManager myHighlightManager;

  private MessagesGutter myMessagesGutter;
  private LeftEditorHighlighter myLeftHighlighter;
  @Nullable
  protected SNode myNode;
  private boolean myNoVirtualFile;

  @Nullable
  protected SNodeReference myNodePointer;
  @NotNull
  private EditorContext myEditorContext;
  @NotNull
  private final EditorConfiguration myEditorConfiguration;
  private final EditorMessageOwner myOwner = new EditorMessageOwner() {
  };

  private IntentionsSupport myIntentionsSupport;
  @SuppressWarnings({"UnusedDeclaration"})
  private AutoValidator myAutoValidator;
  private SearchPanel mySearchPanel = null;
  private JPanel myUpperPanel = null;
  private Map<String, JComponent> myUpperComponents = new HashMap<>();
  @SuppressWarnings({"UnusedDeclaration"})
  private ReferenceUnderliner myReferenceUnderliner = new ReferenceUnderliner();
  private BracesHighlighter myBracesHighlighter = new BracesHighlighter(this);
  private boolean myPopupMenuEnabled = true;
  private boolean myIsInFiguresHierarchy = false;

  private KeymapHandler<KeyEvent> myKeymapHandler = new AWTKeymapHandler();
  private ActionHandler myActionHandler = new ActionHandlerImpl(this);

  @NotNull
  private final EditorHighlighter myHighlighter = new EditorHighlighter(this);

  @NotNull
  private final EditorComponentFocusTracker myFocusTracker = new EditorComponentFocusTracker(this);

  public EditorComponent(@NotNull SRepository repository) {
    this(repository, EditorConfigurationBuilder.buildDefault());
  }

  protected EditorComponent(@NotNull SRepository repository, @NotNull EditorConfiguration configuration) {
    myRepository = repository;
    myEditorConfiguration = configuration;
    myCommandContext = createCommandContext();
    myUpdater = createUpdater(myCommandContext);
    myHighlightManager = new NodeHighlightManager(this);

    if (ApplicationManager.getApplication() != null && ApplicationManager.getApplication().getComponent(MPSCoreComponents.class) != null) {
      myClassLoaderManager = ApplicationManager.getApplication().getComponent(MPSCoreComponents.class).getClassLoaderManager();
    } else {
      LOG.warning("ClassloaderManager is not found, the reload will be switched off");
    }

    setLayout(new EditorComponentLayoutManager(this));
    setEditorContext(null, repository);
    myRootCell = new EditorCell_Constant(getEditorContext(), null, "");
    myRootCell.setSelectable(false);

    setBackground(StyleRegistry.getInstance().getEditorBackground());

    setFocusCycleRoot(true);
    setFocusTraversalPolicy(new FocusTraversalPolicy() {
      @Override
      public Component getComponentAfter(Container aContainer, Component aComponent) {
        if (myIsInFiguresHierarchy) {
          executeComponentAction(CellActionType.NEXT);
        }
        return myIsInFiguresHierarchy ? aContainer : null;
      }

      @Override
      public Component getComponentBefore(Container aContainer, Component aComponent) {
        if (myIsInFiguresHierarchy) {
          executeComponentAction(CellActionType.PREV);
        }
        return myIsInFiguresHierarchy ? aContainer : null;
      }

      @Override
      public Component getFirstComponent(Container aContainer) {
        return myIsInFiguresHierarchy ? aContainer : null;
      }

      @Override
      public Component getLastComponent(Container aContainer) {
        return myIsInFiguresHierarchy ? aContainer : null;
      }

      @Override
      public Component getDefaultComponent(Container aContainer) {
        return myIsInFiguresHierarchy ? aContainer : null;
      }
    });
    setFocusTraversalKeysEnabled(false);

    setDoubleBuffered(true);

    myNodeSubstituteChooser = new NodeSubstituteChooser(this);

    // --- keyboard handling ---
    myKbdHandlersStack = new Stack<>();
    myKbdHandlersStack.push(new EditorComponentKeyboardHandler(myKeymapHandler));

    registerKeyboardAction(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        goToNextErrorCell(false);
      }
    }, KeyStroke.getKeyStroke("F2"), WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    registerKeyboardAction(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        goToNextErrorCell(true);
      }
    }, KeyStroke.getKeyStroke("shift F2"), WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    registerKeyboardAction(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        goToNextHighlightedCell(false);
      }
    }, KeyStroke.getKeyStroke("F3"), WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    registerKeyboardAction(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        goToNextHighlightedCell(true);
      }
    }, KeyStroke.getKeyStroke("shift F3"), WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(final MouseEvent e) {
        if (areMouseEventsBlocked()) {
          return;
        }
        if (e.isPopupTrigger()) {
          processPopupMenu(e);
        } else {
          processMousePressed(e);
        }
      }

      @Override
      public void mouseClicked(MouseEvent e) {
        if (areMouseEventsBlocked()) {
          return;
        }
        jetbrains.mps.openapi.editor.cells.EditorCell selectedCell = getSelectedCell();
        boolean inSelectedCell = selectedCell != null && myRootCell.findLeaf(e.getX(), e.getY()) == selectedCell;
        if (inSelectedCell) {
          Selection selection = getSelectionManager().getSelection();
          if (selection.canExecuteAction(CellActionType.CLICK)) {
            selection.executeAction(CellActionType.CLICK);
          } else if (e.getClickCount() == 2 && selectedCell instanceof EditorCell_Label) {
            ((EditorCell_Label) selectedCell).selectWordOrAll();
            repaintExternalComponent();
          }
        }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        if (areMouseEventsBlocked()) {
          return;
        }
        if (e.isPopupTrigger()) {
          processPopupMenu(e);
        }
        super.mouseReleased(e);
      }
    });

    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(final KeyEvent e) {
        processKeyPressed(e);
      }

      @Override
      public void keyTyped(KeyEvent e) {
        processKeyTyped(e);
      }

      @Override
      public void keyReleased(final KeyEvent e) {
        processKeyReleased(e);
      }
    });

    addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) {
        if (isDisposed()) {
          return;
        }
        setDefaultSelection();
        activateCaretBlinker();
      }

      @Override
      public void focusLost(FocusEvent e) {
        closeSubstituteChooser(e.getOppositeComponent());
        commitAllCellValues();
        deActivateCaretBlinker();
      }
    });

    myAutoValidator = new AutoValidator(this);
    attachListeners();
    enablePasteFromHistory();

    if (configuration.withUI) {
      createUI(configuration);
    }

  }

  // TODO:
  // - extract all UI-free common logic into a super-class (AbstractEditorComponent)
  // - let HeadlessEditorComponent extend AbstractEditorComponent
  // - make this method again a part of constructor for this class
  private void createUI(EditorConfiguration editorConfiguration) {
    myVerticalScrollBar = new MyScrollBar(Adjustable.VERTICAL);

    myScrollPane = ScrollPaneFactory.createScrollPane();
    if (editorConfiguration.rightToLeft) {
      myScrollPane.setLayout(new LeftHandScrollbarLayout());
    }
    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    myScrollPane.setVerticalScrollBar(myVerticalScrollBar);
    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    myScrollPane.setViewportView(this);
    myScrollPane.getViewport().addChangeListener(new ChangeListener() {

      @Override
      public void stateChanged(ChangeEvent e) {
        if (!getNodeSubstituteChooser().isVisible()) {
          return;
        }
        Point point = getNodeSubstituteChooser().calcPatternEditorLocation();
        Rectangle viewRect = getViewport().getViewRect();
        if (isInsideEditor(point, viewRect)) {
          getNodeSubstituteChooser().moveToContextCell();
        } else {
          deactivateSubstituteChooser();
        }
      }

      private boolean isInsideEditor(Point point, Rectangle viewRect) {
        return isShowing() && point != null
               && point.getX() >= 0 && point.getX() <= getLocationOnScreen().getX() + viewRect.getX() + viewRect.getWidth()
               && point.getY() >= 0 &&
               point.getY() <= getLocationOnScreen().getY() + viewRect.getY() + viewRect.getHeight() + myScrollPane.getHorizontalScrollBar().getHeight();
      }
    });

    myContainer = new JPanel() {
      @Override
      public void addNotify() {
        super.addNotify();
        myIsInFiguresHierarchy = true;
      }

      @Override
      public void removeNotify() {
        myIsInFiguresHierarchy = false;
        super.removeNotify();
      }
    };
    myContainer.setMinimumSize(new Dimension(0, 0));
    myContainer.setLayout(new BorderLayout());
    myContainer.add(myScrollPane, BorderLayout.CENTER);

    myMessagesGutter = new MessagesGutter(this, editorConfiguration.rightToLeft);
    if (editorConfiguration.showErrorsGutter) {
      getVerticalScrollBar().setPersistentUI(myMessagesGutter);
    } else {
      getVerticalScrollBar().setPersistentUI(new ButtonlessScrollBarUI() {
        @Override
        public boolean alwaysShowTrack() {
          return true;
        }
      });
    }
    myLeftHighlighter = new LeftEditorHighlighter(this, editorConfiguration.rightToLeft);
    myLeftHighlighter.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        for (LeftMarginMouseListener listener : new ArrayList<>(myLeftMarginPressListeners)) {
          listener.mousePressed(e, EditorComponent.this);
        }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        for (LeftMarginMouseListener listener : new ArrayList<>(myLeftMarginPressListeners)) {
          listener.mouseReleased(e, EditorComponent.this);
        }
      }

      @Override
      public void mouseClicked(MouseEvent e) {
        for (LeftMarginMouseListener listener : new ArrayList<>(myLeftMarginPressListeners)) {
          listener.mouseClicked(e, EditorComponent.this);
        }
      }
    });
    myScrollPane.setRowHeaderView(myLeftHighlighter);

    myIntentionsSupport = new IntentionsSupport(this);

    if (MPSToolTipManager.getInstance() != null) {
      MPSToolTipManager.getInstance().registerComponent(this);
    }

    getSelectionManager().addSelectionListener((editorComponent, oldSelection, newSelection) -> {
      if (oldSelection == newSelection) {
        return;
      }
      deactivateSubstituteChooser();
      updateStatusBarMessage();

      if (oldSelection != null) {
        for (jetbrains.mps.openapi.editor.cells.EditorCell editorCell : oldSelection.getSelectedCells()) {
          repaint(editorCell);
        }
      }
      if (newSelection != null) {
        for (jetbrains.mps.openapi.editor.cells.EditorCell editorCell : newSelection.getSelectedCells()) {
          repaint(editorCell);
        }
      }
      myLeftHighlighter.repaint();
    });
  }

  boolean hasUI() {
    return myEditorConfiguration.withUI;
  }

  private void enablePasteFromHistory() {
    // Registering DefaultEditorKit.pasteAction in the action map enables 'Paste from History'
    getActionMap().put(DefaultEditorKit.pasteAction, new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        performPaste();
      }
    });
  }

  protected UpdaterImpl createUpdater(CommandContextImpl commandContext) {
    return new UpdaterImpl(this, commandContext);
  }

  protected CommandContextImpl createCommandContext() {
    return new CommandContextImpl(this);
  }

  protected void attachListeners() {
    EditorSettings.getInstance().addEditorSettingsListener(mySettingsListener);
    if (myClassLoaderManager != null) {
      myClassLoaderManager.addListener(myClassesListener);
    }
  }

  protected void notifyCreation() {
    jetbrains.mps.project.Project project = ProjectHelper.getProject(myRepository);
    if (project == null) {
      return;
    }
    Project ideaProject = ProjectHelper.toIdeaProject(project);
    if (ideaProject == null) {
      return;
    }
    EditorComponentCreateListener listener = ideaProject.getMessageBus().syncPublisher(EditorComponentCreateListener.EDITOR_COMPONENT_CREATION);
    listener.editorComponentCreated(EditorComponent.this);
  }

  protected void notifyDisposal() {
    jetbrains.mps.project.Project project = ProjectHelper.getProject(myRepository);
    if (project == null) {
      return;
    }
    if (project.isDisposed()) {
      LOG.error("Trying to notify disposal of EditorComponent related to disposed project. This may cause memory leaks.");
      return;
    }
    Project ideaProject = ProjectHelper.toIdeaProject(project);
    if (ideaProject == null) {
      return;
    }
    EditorComponentCreateListener listener = ideaProject.getMessageBus().syncPublisher(EditorComponentCreateListener.EDITOR_COMPONENT_CREATION);
    listener.editorComponentDisposed(this);
  }

  public boolean onEscape() {
    return false;
  }

  public int getShiftX() {
    return myShiftX;
  }

  public JViewport getViewport() {
    assert hasUI();
    return myScrollPane.getViewport();
  }

  Point getViewPosition() {
    return hasUI() ? getViewport().getViewPosition() : new Point(0, 0);
  }

  void setViewPosition(Point point) {
    if (!hasUI()) {
      return;
    }
    getViewport().setViewPosition(point);
  }

  @NotNull
  public MyScrollBar getVerticalScrollBar() {
    assert hasUI();
    return myVerticalScrollBar;
  }

  @Override
  public SNode getSelectedNode() {
    jetbrains.mps.openapi.editor.cells.EditorCell selectedCell = getSelectedCell();
    if (selectedCell == null) {
      return null;
    }
    return selectedCell.getSNode();
  }

  @Override
  public List<SNode> getSelectedNodes() {
    Selection selection = mySelectionManager.getSelection();
    return selection != null ? selection.getSelectedNodes() : Collections.emptyList();
  }

  public String[] getEditorHintsForNode(SNode node) {
    jetbrains.mps.openapi.editor.cells.EditorCell nodeCell = findNodeCell(node);
    if (nodeCell != null) {
      EditorCellContext cellContext = nodeCell.getCellContext();
      if (cellContext == null) {
        return null;
      }
      final Collection<String> nodeContextHints = cellContext.getHints();
      return nodeContextHints.toArray(new String[0]);
    }
    return null;
  }

  public EditorMessageOwner getHighlightMessagesOwner() {
    return myOwner;
  }

  private void goToNextErrorCell(boolean backwards) {
    if (getSelectedCell() == null) {
      return;
    }
    new CellNavigator(this) {
      @Override
      boolean isSuitableCell(jetbrains.mps.openapi.editor.cells.EditorCell cell) {
        return CellMessagesUtil.hasErrorMessages(cell);

      }
    }.goToNextCell(backwards);
  }

  private void goToNextHighlightedCell(boolean backwards) {
    if (getSelectedCell() == null) {
      return;
    }
    new CellNavigator(this) {
      @Override
      boolean isSuitableCell(jetbrains.mps.openapi.editor.cells.EditorCell cell) {
        for (SimpleEditorMessage m : getHighlightManager().getMessagesFor(cell.getSNode())) {
          if (m.getOwner() == getHighlightMessagesOwner()) {
            return true;
          }
        }
        return false;
      }
    }.goToNextCell(backwards);
  }

  @Override
  public SNode getEditedNode() {
    return myNode;
  }

  /**
   * From now on only NodeEditorComponent has virtual file.
   * This method will be removed in the next release.
   *
   * @deprecated since MPS 3.4
   */
  @Deprecated
  @Nullable
  public MPSNodeVirtualFile getVirtualFile() {
    return null;
  }

  @Override
  public void touch() {
  }

  @Override
  public SNodeReference getEditedNodePointer() {
    return myNodePointer;
  }

  @Override
  public String getMPSTooltipText(final MouseEvent event) {
    final Reference<String> rv = new Reference<>(null);
    getModelAccess().runReadAction(new CancellableReadAction() {
      @Override
      protected void execute() {
        if (isDisposed()) {
          return;
        }

        jetbrains.mps.openapi.editor.cells.EditorCell cell = myRootCell.findLeaf(event.getX(), event.getY());
        if (cell == null) {
          return;
        }
        if (isCancelRequested()) {
          confirmCancel();
          return;
        }
        rv.set(getMessagesTextFor(cell));
      }
    });
    return rv.get();
  }

  @Override
  public Point getToolTipLocation(final MouseEvent event) {
    final Reference<Point> rv = new Reference<>(null);
    getModelAccess().runReadAction(new CancellableReadAction() {
      @Override
      protected void execute() {
        if (isDisposed()) {
          return;
        }

        jetbrains.mps.openapi.editor.cells.EditorCell cell = myRootCell.findLeaf(event.getX(), event.getY());
        if (cell == null) {
          return;
        }
        if (isCancelRequested()) {
          confirmCancel();
          return;
        }
        if (getMessagesTextFor(cell) != null) {
          rv.set(new Point(event.getX(), event.getY()));
        }
      }
    });
    return rv.get();
  }

  public void updateStatusBarMessage() {
    if (!isFocusOwner()) {
      return;
    }
    getModelAccess().runReadInEDT(() -> {
      if (!isFocusOwner() || getCurrentProject() == null || isProjectDisposed()) {
        return;
      }

      jetbrains.mps.openapi.editor.cells.EditorCell selection = getSelectedCell();
      String info = "";
      if (selection != null) {
        HighlighterMessage message = getHighlighterMessageFor(selection);
        if (message != null) {
          info = message.getMessage();
        }
      }

      jetbrains.mps.project.Project project = getCurrentProject();
      IdeFrame ideFrame = WindowManager.getInstance().getIdeFrame(ProjectHelper.toIdeaProject(project));
      StatusBarEx statusBar = (StatusBarEx) ideFrame.getStatusBar();

      //current info is significant or the editor removes its own message
      if (!info.isEmpty() || myLastWrittenStatus.equals(statusBar.getInfo())) {
        statusBar.setInfo(info);
        if (!info.isEmpty()) {
          myLastWrittenStatus = info;
        }
      }
    });
  }

  @Deprecated
  @Override
  public TypeCheckingContext createTypecheckingContext(SNode sNode, TypeContextManager typeContextManager) {
    return (new DefaultTypecheckingContextOwner()).createTypecheckingContext(sNode, typeContextManager);
  }

  @Deprecated
  @Override
  public boolean reuseTypecheckingContext() {
    return true;
  }

  @Deprecated
  @Override
  public SubtypingCache createSubtypingCache() {
    return new ConcurrentSubtypingCache();
  }

  public String getMessagesTextFor(jetbrains.mps.openapi.editor.cells.EditorCell cell) {
    List<HighlighterMessage> messages = getHighlighterMessagesFor(cell);
    if (messages.isEmpty()) {
      return null;
    }
    StringBuilder result = new StringBuilder();
    for (ListIterator<HighlighterMessage> it = messages.listIterator(messages.size()); it.hasPrevious(); ) {
      SimpleEditorMessage message = it.previous();
      if (result.length() != 0) {
        result.append('\n');
      }
      result.append(message.getMessage());
    }
    return result.toString();
  }

  @NotNull
  private List<HighlighterMessage> getHighlighterMessagesFor(jetbrains.mps.openapi.editor.cells.EditorCell cell) {
    jetbrains.mps.openapi.editor.cells.EditorCell parent = cell;
    while (parent != null) {
      if (cell.getBottom() < parent.getBottom() && parent.getSNode() != cell.getSNode()) {
        return Collections.emptyList();
      }
      List<HighlighterMessage> messages = CellMessagesUtil.getMessages(parent, HighlighterMessage.class);
      if (!messages.isEmpty()) {
        return messages;
      }
      parent = parent.getParent();
    }

    return Collections.emptyList();
  }

  private HighlighterMessage getHighlighterMessageFor(jetbrains.mps.openapi.editor.cells.EditorCell cell) {
    List<HighlighterMessage> messages = getHighlighterMessagesFor(cell);
    ListIterator<HighlighterMessage> it = messages.listIterator(messages.size());
    return it.hasPrevious() ? it.previous() : null;
  }

  /*
    sorted by severity, from lower to high
   */
  public Collection<IssueKindReportItem> getReportItemsForCell(jetbrains.mps.openapi.editor.cells.EditorCell cell) {
    List<HighlighterMessage> messages = getHighlighterMessagesFor(cell);
    return messages.stream().map(HighlighterMessage::getReportItem).collect(Collectors.toList());
  }

  public void showMessageTooltip() {
    jetbrains.mps.openapi.editor.cells.EditorCell cell = getSelectedCell();
    if (cell == null) {
      return;
    }
    String text = getMessagesTextFor(cell);
    Point point = new Point(cell.getX(), cell.getY() + cell.getHeight());
    if (MPSToolTipManager.getInstance() != null) {
      MPSToolTipManager.getInstance().showToolTip(text, this, point);
    }
  }

  public void hideMessageToolTip() {
    if (MPSToolTipManager.getInstance() != null) {
      MPSToolTipManager.getInstance().hideToolTip();
    }
  }

  protected boolean notifiesCreation() {
    return false;
  }

  public void editNode(final SNode node) {
    if (isDisposed()) {
      return;
    }
    clearModelDisposedTrace();

    getModelAccess().runReadAction(() -> {
      if (node != null) {
        assert node.getModel() != null : "Can't edit a node that is not registered in a model";
        assert SNodeUtil.isAccessible(node, myRepository) :
            "editNode() accepts nodes from its own repository only (model = " + node.getModel() + ", repository = " + node.getModel().getRepository() + ")";
      }

      if (myNode != null && notifiesCreation()) {
        notifyDisposal();
      }

      final boolean needNewTypecheckingContext = updateContainingRoot(node);
      if (needNewTypecheckingContext && myTypecheckingSession != null) {
        myTypecheckingSession.release();
        myTypecheckingSession = null;
      }

      myNode = node;
      if (myNode != null) {
        myNodePointer = myNode.getReference();
        SModel model = node.getModel();
        assert model != null : "Can't edit a node that is not registered in a model";
        setEditorContext(model, myRepository);
        myReadOnly = model.isReadOnly();
      } else {
        myNodePointer = null;
        setEditorContext(null, myRepository);
        myReadOnly = true;
      }
      myCommandContext.updateContextNode();

      if (needNewTypecheckingContext && myNode != null) {
        myTypecheckingSession = TypecheckingFacade
                                    .getFromContext()
                                    .requestNewSession(TypecheckingSession.Flags.forRoot(getNodeForTypechecking()).incremental());
      }

      rebuildEditorContent();

      if (myNode != null && notifiesCreation()) {
        notifyCreation();
      }
    });
  }

  public void addAdditionalPainter(AdditionalPainter additionalPainter) {
    synchronized (myAdditionalPaintersLock) {
      if (!myAdditionalPainters.contains(additionalPainter)) {
        myAdditionalPainters.add(additionalPainter);
        myItemsToAdditionalPainters.put(additionalPainter.getItem(), additionalPainter);
        additionalPainter.afterAdding(this);
      }
    }
  }

  public void removeAdditionalPainter(AdditionalPainter additionalPainter) {
    synchronized (myAdditionalPaintersLock) {
      if (myAdditionalPainters.contains(additionalPainter)) {
        additionalPainter.beforeRemoval(this);
        myAdditionalPainters.remove(additionalPainter);
        myItemsToAdditionalPainters.remove(additionalPainter.getItem());
      }
    }
  }

  public void removeAdditionalPainterByItem(Object item) {
    synchronized (myAdditionalPaintersLock) {
      AdditionalPainter additionalPainter = myItemsToAdditionalPainters.get(item);
      if (additionalPainter != null) {
        additionalPainter.beforeRemoval(this);
        myItemsToAdditionalPainters.remove(item);
        myAdditionalPainters.remove(additionalPainter);
      }
    }
  }

  public Color getAdditionalCellFontColor(@NotNull EditorCell_Label cell) {
    synchronized (myAdditionalPaintersLock) {
      for (AdditionalPainter additionalPainter : myAdditionalPainters) {
        Rectangle coverageArea = additionalPainter.getCoverageArea(this);
        if (coverageArea != null) {
          if (coverageArea.contains(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight())) {
            Color color = additionalPainter.getCellsFontColor(cell);
            if (color != null) {
              return color;
            }
          }
        }
      }
      return null;
    }
  }

  public AdditionalPainter getAdditionalPainterByItem(Object item) {
    synchronized (myAdditionalPaintersLock) {
      return myItemsToAdditionalPainters.get(item);
    }
  }

  public List<AdditionalPainter> getAdditionalPainters() {
    List<AdditionalPainter> result;
    synchronized (myAdditionalPaintersLock) {
      result = new ArrayList<>(myAdditionalPainters);
    }
    return result;
  }

  // TODO pool this method up to NodeEditorComponent
  @NotNull
  public MessagesGutter getMessagesGutter() {
    assert hasUI();
    return myMessagesGutter;
  }

  @NotNull
  public LeftEditorHighlighter getLeftEditorHighlighter() {
    assert hasUI();
    return myLeftHighlighter;
  }

  @NotNull
  public SearchPanel getSearchPanel() {
    assert hasUI();
    if (mySearchPanel == null) {
      mySearchPanel = new SearchPanel(this);
    }
    return mySearchPanel;
  }

  public boolean isSearchPanelVisible() {
    return mySearchPanel != null && mySearchPanel.isVisible();
  }

  public JPanel getUpperPanel() {
    assert hasUI();
    if (myUpperPanel == null) {
      myUpperPanel = new JPanel();
      myUpperPanel.setLayout(new GridLayout(0, 1));
      myContainer.add(myUpperPanel, BorderLayout.NORTH);
    }
    return myUpperPanel;
  }

  public void addUpperComponent(JComponent component) {
    getUpperPanel().add(component);
  }

  public void addUpperComponent(JComponent component, String id) {
    getUpperPanel().add(component);
    myUpperComponents.put(id, component);
  }

  public void removeUpperComponent(JComponent component) {
    if (myUpperPanel == null) {
      return;
    }
    getUpperPanel().remove(component);
    for (String key : new HashSet<>(myUpperComponents.keySet())) {
      if (component == myUpperComponents) {
        myUpperComponents.remove(key);
      }
    }
  }

  public void removeUpperComponent(String id) {
    JComponent component = myUpperComponents.get(id);
    if (component != null) {
      removeUpperComponent(component);
    }
  }

  protected Set<SimpleEditorMessage> getMessages() {
    return new LinkedHashSet<>(myHighlightManager.getMessages());
  }

  private EditorCell_WithComponent findCellForComponent(Component component, jetbrains.mps.openapi.editor.cells.EditorCell root) {
    if (root instanceof EditorCell_WithComponent && ((EditorCell_WithComponent) root).getComponent() == component) {
      return (EditorCell_WithComponent) root;
    }

    if (root instanceof EditorCell_Collection) {
      EditorCell_Collection collection = (EditorCell_Collection) root;
      for (jetbrains.mps.openapi.editor.cells.EditorCell cell : collection) {
        EditorCell_WithComponent result = findCellForComponent(component, cell);
        if (result != null) {
          return result;
        }
      }
    }

    return null;
  }

  private void processPopupMenu(final MouseEvent e) {
    getModelAccess().runReadAction(() -> showPopupMenu(e));
  }

  private void showPopupMenu(MouseEvent e) {
    if (!myPopupMenuEnabled) {
      return;
    }
    ActionGroup baseGroup = ActionUtils.getDefaultGroup(myDefaultPopupGroupId);
    if (baseGroup == null) {
      return;
    }

    baseGroup.setPopup(false);

    DefaultActionGroup group = ActionUtils.groupFromActions(
        baseGroup,
        new Separator(),
        getCellActionsGroup()
    );

    JPopupMenu popupMenu = ActionManager.getInstance().createActionPopupMenu(ActionPlaces.EDITOR_POPUP, group).getComponent();
    popupMenu.show(EditorComponent.this, e.getX(), e.getY());
    e.consume();
  }

  protected String getDefaultPopupGroupId() {
    return myDefaultPopupGroupId;
  }

  protected void setDefaultPopupGroupId(String id) {
    myDefaultPopupGroupId = id;
  }

  private DefaultActionGroup getCellActionsGroup() {
    DefaultActionGroup result = new DefaultActionGroup("Cell actions", true);
    result.setPopup(false);
    jetbrains.mps.openapi.editor.cells.EditorCell cell = getSelectedCell();

    final EditorContext editorContext = createEditorContextForActions();
    for (final KeyMapAction action : myKeymapHandler.getAllRegisteredActions(cell, editorContext)) {
      try {
        if (!(action.isShownInPopupMenu() && action.canExecute(editorContext))) {
          continue;
        }
        BaseAction mpsAction = new MyBaseAction(action, editorContext);
        mpsAction.addPlace(ActionPlace.EDITOR);
        result.add(mpsAction);
      } catch (Throwable t) {
        LOG.error(t);
      }
    }
    return result;
  }

  private EditorContext createEditorContextForActions() {
    return new EditorContext(this, getEditedNode() != null ? getEditedNode().getModel() : null, myRepository, myEditorConfiguration,
                             new DisabledContextAssistantManager());
  }

  @NotNull
  public JComponent getExternalComponent() {
    assert hasUI();
    return myContainer;
  }

  public void repaintExternalComponent() {
    if (!hasUI()) {
      return;
    }
    getExternalComponent().repaint();
  }

  public void validateExternalComponent() {
    if (!hasUI()) {
      return;
    }
    getExternalComponent().validate();
  }

  @NotNull
  @Override
  public EditorContext getEditorContext() {
// TODO: uncomment this assertion. Was commented out because this method is called indirectly from the dispose() method (failing tests).
//    assert !isDisposed();
    return myEditorContext;
  }

  @NotNull
  protected EditorConfiguration getEditorConfiguration() {
    return myEditorConfiguration;
  }

  @NotNull
  protected SRepository getRepository() {
    return myRepository;
  }

  /**
   * Creating a cell representing empty editor content. Empty means editor has no node (getEditedNode() == null)
   * or currently editing node is not within a model (getEditedNode().getModel() == null)
   *
   * @return new EditorCell
   */
  public jetbrains.mps.openapi.editor.cells.EditorCell createEmptyCell() {
    return new EditorCell_Constant(getEditorContext(), getEditedNode(), getEditedNode() == null ? "<no node>" : "<node is not inside a model>");
  }

  public void setCollapseState(jetbrains.mps.openapi.editor.cells.EditorCell cell, Boolean collapsed) {
    if (collapsed == null) {
      resetCollapseState(cell);
    } else {
      myCollapseStates.put(cell, collapsed);
    }
    for (AdditionalPainter painter : getAdditionalPainters()) {
      painter.onUpdate(this);
    }
  }

  public void resetCollapseState(jetbrains.mps.openapi.editor.cells.EditorCell cell) {
    myCollapseStates.remove(cell);
  }

  public List<Pair<jetbrains.mps.openapi.editor.cells.EditorCell, Boolean>> getCollapseStates() {
    List<Pair<jetbrains.mps.openapi.editor.cells.EditorCell, Boolean>> result = new ArrayList<>();
    for (Entry<jetbrains.mps.openapi.editor.cells.EditorCell, Boolean> collapseState : myCollapseStates.entrySet()) {
      result.add(new Pair<>(collapseState.getKey(), collapseState.getValue()));
    }
    return result;
  }

  public void setBracesEnabled(EditorCell cell, boolean enabled) {
    if (enabled) {
      myBracesEnabledCells.add(cell);
    } else {
      myBracesEnabledCells.remove(cell);
    }
  }

  public Set<EditorCell> getBracesEnabledCells() {
    return new HashSet<>(myBracesEnabledCells);
  }

  void clearBracesEnabledCells() {
    myBracesEnabledCells.clear();
  }

  @Override
  public void dispose() {
    assertInEDT();
    if (myDisposed) {
      throw new IllegalStateException(myDisposedTrace);
    }
    fireEditorWillBeDisposed();
    myDisposed = true;
    myDisposedTrace = new Throwable("Editor was disposed by: ");
    if (!RuntimeFlags.isTestMode()) {
      hideMessageToolTip();
    }

    if (myTypecheckingSession != null) {
      myTypecheckingSession.release();
      myTypecheckingSession = null;
    }
    myHighlightManager.dispose();

    detachListeners();
    // we expect this method to be executed at least inside model read
    // TODO: add assertion here
    myAutoValidator.dispose();
    myUpdater.dispose();

    if (hasUI()) {
      myLeftHighlighter.dispose();
      myMessagesGutter.dispose();
    }

    if (myNodeSubstituteChooser != null) {
      myNodeSubstituteChooser.dispose();
    }
    if (myRootCell != null) {
      ((EditorCell_Basic) myRootCell).onRemove();
      myRootCell = null;
    }
    mySelectionManager.dispose();

    myLeftMarginPressListeners.clear();

    myFocusTracker.dispose();

    if (myDeletionApprover != null) {
      myDeletionApprover.dispose();
    }
  }

  protected void detachListeners() {
    EditorSettings.getInstance().removeEditorSettingsListener(mySettingsListener);
    myClassLoaderManager.removeListener(myClassesListener);
  }

  public boolean hasValidSelectedNode() {
    SNode selectedNode = getSelectedNode();
    return selectedNode != null && SNodeUtil.isAccessible(selectedNode, myRepository);
  }

  @Override
  public boolean isDisposed() {
    return myDisposed;
  }

  public void assertModelNotDisposed() {
    // if by any chance you need model access here, use myRepository
    assert myModelDisposedStackTrace == null : getModelDisposedMessage();
    if (myNode == null) {
      return;
    }
    SModel model = myNode.getModel();
    if (model == null) {
      return;
    }
    assert model.getRepository() != null : getNodeDisposedMessage(model);
  }

  private String getNodeDisposedMessage(SModel model) {
    StringBuilder sb = new StringBuilder("editor (" + this + ") is invalid");
    if (myNode != null) {
      sb.append(", myNode is disposed");
      StackTraceElement[] modelDisposedTrace = ((jetbrains.mps.smodel.SModelInternal) model).getDisposedStacktrace();
      if (modelDisposedTrace != null) {
        for (StackTraceElement element : modelDisposedTrace) {
          sb.append("\nat ");
          sb.append(element);
        }
      }
    } else {
      sb.append(", myNode == null");
    }
    sb.append("____________________________");
    return sb.toString();
  }

  private String getModelDisposedMessage() {
    StringBuilder sb = new StringBuilder("Model was disposed through:");
    for (StackTraceElement element : myModelDisposedStackTrace) {
      sb.append("\nat ");
      sb.append(element);
    }
    sb.append('\n');
    sb.append("EditorComponent.myDisposed == ");
    sb.append(isDisposed());
    sb.append('\n');
    return sb.toString();
  }

  // This method should be called each time we set new node for and editor
  protected void clearModelDisposedTrace() {
    myModelDisposedStackTrace = null;
  }

  public void setModelDisposedTrace(StackTraceElement[] trace) {
    myModelDisposedStackTrace = trace;
  }

  /*
    Can be used to check if editor is in valid state or not.
    Editor can be in invalid state then corresponding model
    was reloaded, but current editor instance was not
    updated yet.
   */
  public boolean isInvalid() {
    return isInvalidLightweight() || !SNodeUtil.isAccessible(getEditedNode(), myRepository);
  }

  /*
    Lightweight check for editor validity state. Similar to isInvalid,
    but can be called outside of read action.
   */
  private boolean isInvalidLightweight() {
    return isDisposed() || getEditedNode() == null;
  }

  public void setRootCell(@NotNull jetbrains.mps.openapi.editor.cells.EditorCell rootCell) {
    if (getComponents().length > 0) {
      removeAll();
    }
    ((EditorCell_Basic) myRootCell).onRemove();

    myRootCell = (EditorCell) rootCell;

    ((EditorCell_Basic) myRootCell).onAdd();
    for (EditorCell_WithComponent component : getCellTracker().getComponentCells()) {
      add(component.getComponent());
    }
    for (AdditionalPainter painter : getAdditionalPainters()) {
      painter.onUpdate(this);
    }
  }

  @Override
  public EditorCell getRootCell() {
    return myRootCell;
  }

  @NotNull
  public NodeHighlightManager getHighlightManager() {
    return myHighlightManager;
  }

  public CellActionType getActionType(KeyEvent keyEvent, EditorContext editorContext) {
    if (keyPressed(keyEvent) && keyEvent.getKeyCode() == KeyEvent.VK_HOME && shiftDown(keyEvent)) {
      return CellActionType.SELECT_HOME;
    }
    if (keyPressed(keyEvent) && keyEvent.getKeyCode() == KeyEvent.VK_END && shiftDown(keyEvent)) {
      return CellActionType.SELECT_END;
    }
    if (keyPressed(keyEvent) && keyEvent.getKeyCode() == KeyEvent.VK_PAGE_DOWN && noKeysDown(keyEvent)) {
      return CellActionType.PAGE_DOWN;
    }
    if (keyPressed(keyEvent) && keyEvent.getKeyCode() == KeyEvent.VK_PAGE_UP && noKeysDown(keyEvent)) {
      return CellActionType.PAGE_UP;
    }
    if (keyPressed(keyEvent) && keyEvent.getKeyCode() == KeyEvent.VK_TAB && noKeysDown(keyEvent)) {
      return CellActionType.NEXT;
    }
    if (keyPressed(keyEvent) && keyEvent.getKeyCode() == KeyEvent.VK_TAB && shiftDown(keyEvent)) {
      return CellActionType.PREV;
    }
    if (keyEvent.getModifiers() == KeyEvent.CTRL_MASK && keyEvent.getKeyCode() == KeyEvent.VK_F1) {
      return CellActionType.SHOW_MESSAGE;
    }

    // ---
    if (keyTyped(keyEvent) && keyEvent.getKeyChar() == ' ' && noKeysDown(keyEvent)) {
      jetbrains.mps.openapi.editor.cells.EditorCell selectedCell = editorContext.getNodeEditorComponent().getSelectedCell();

      if (!(selectedCell instanceof EditorCell_STHint)) {
        if (!(selectedCell instanceof EditorCell_Label)) {
          return CellActionType.RIGHT_TRANSFORM;
        }
        EditorCell_Label labelCell = (EditorCell_Label) selectedCell;

        // caret at the end of text ?
        String text = labelCell.getText();
        int caretPosition = labelCell.getCaretPosition();
        //System.out.println("text:" + text + " len:" + text.length() + "caret at:" + caretPosition);
        if (caretPosition == text.length()) {
          if (caretPosition == 0 && labelCell instanceof EditorCell_Constant) {
            //empty unbound constant cells should ignore the space key when empty
            return CellActionType.SELECT_END;
          } else {
            return CellActionType.RIGHT_TRANSFORM;
          }
        }

        if (caretPosition == 0) {
          return CellActionType.LEFT_TRANSFORM;
        }
      }
    }
    return null;
  }

  private boolean ctrlShiftDown(KeyEvent keyEvent) {
    return keyEvent.getModifiers() == (KeyEvent.CTRL_MASK + KeyEvent.SHIFT_MASK);
  }

  private boolean shiftDown(KeyEvent keyEvent) {
    return keyEvent.getModifiers() == KeyEvent.SHIFT_MASK;
  }

  private boolean noKeysDown(KeyEvent keyEvent) {
    return keyEvent.getModifiers() == 0;
  }

  private boolean keyTyped(KeyEvent keyEvent) {
    return keyEvent.getID() == KeyEvent.KEY_TYPED;
  }

  private boolean keyPressed(KeyEvent keyEvent) {
    return keyEvent.getID() == KeyEvent.KEY_PRESSED;
  }

  private boolean ctrlDown(KeyEvent keyEvent) {
    return keyEvent.getModifiers() == KeyEvent.CTRL_MASK;
  }

  boolean executeComponentAction(CellActionType type) {
    final CellAction action = getComponentAction(type);
    if (action != null && action.executeInCommand()) {
      getModelAccess().executeCommand(new EditorCommand(getCommandContext()) {
        @Override
        protected void doExecute() {
          action.execute(getEditorContext());
        }
      });
      return true;
    }
    return false;
  }


  @Override
  public CellAction getComponentAction(final CellActionType type) {
    //todo ensure that this method is called only from EDT, write the contract and then get rid of synchronization
    if (myEditorComponentActions == null) {
      synchronized (myEditorComponentActionsLock) {
        if (myEditorComponentActions == null) {
          myEditorComponentActions = new EditorComponentActions(this);
        }
      }
    }
    return myEditorComponentActions.getComponentAction(type);
  }

  public void relayout() {
    doRelayout();
    revalidate();
    repaintExternalComponent();
  }

  private void doRelayout() {
    // TODO: check for myDisposed in all methods calling this one
    if (isDisposed()) {
      return;
    }

    myRootCell.setX(myShiftX);
    myRootCell.setY(myShiftY);
    myRootCell.relayout();
    if (!hasUI()) {
      return;
    }
    myLeftHighlighter.relayout(true);
    if (mySearchPanel != null && mySearchPanel.isVisible()) {
      mySearchPanel.search(false);
    }
  }

  public void leftHighlightCell(EditorCell cell, Color c) {
    if (!hasUI()) {
      return;
    }
    myLeftHighlighter.highlight(cell, cell, c);
  }

  public void leftHighlightCells(EditorCell cell, EditorCell cell2, Color c) {
    if (!hasUI()) {
      return;
    }
    myLeftHighlighter.highlight(cell, cell2, c);
  }

  public void leftUnhighlightCell(EditorCell cell) {
    if (!hasUI()) {
      return;
    }
    myLeftHighlighter.unHighlight(cell);
  }

  @Override
  public void selectNode(final SNode node) {
    getModelAccess().runReadAction(() -> {
      EditorCell nodeCell = findNodeCell(node);
      if (nodeCell != null) {
        changeSelection(nodeCell);
      }
    });
  }

  public void selectNode(final SNode node, final String cellId) {
    getModelAccess().runReadAction(() -> {
      EditorCell nodeCell = findCellWithId(node, cellId);
      if (nodeCell != null) {
        changeSelection(nodeCell);
      }
    });
  }

  @Override
  @Nullable
  public EditorCell findNodeCell(final SNode node) {
    return findNodeCell(node, false);
  }

  @Override
  @Nullable
  public EditorCell findNodeCell(final SNode node, boolean findUnderFolded) {
    jetbrains.mps.openapi.editor.cells.EditorCell bigCell = myUpdater.getBigCell(node);
    if (bigCell != null && (bigCell.getRootParent() != getRootCell() || (!findUnderFolded && CellTraversalUtil.getFoldedParent(bigCell) != null))) {
      return null;
    }
    return (EditorCell) bigCell;
  }

  public EditorCell findNodeCellWithRole(SNode node, String role) {
    EditorCell rootCell = findNodeCell(node);
    if (rootCell == null) {
      return null;
    }
    return (EditorCell) findNodeCellWithRole(rootCell, role, node);
  }

  private jetbrains.mps.openapi.editor.cells.EditorCell findNodeCellWithRole(jetbrains.mps.openapi.editor.cells.EditorCell rootCell, String role,
                                                                             SNode node) {
    if (role == null) {
      return null;
    }
    if (role.equals(rootCell.getRole()) && node == rootCell.getSNode()) {
      return rootCell;
    }
    if (rootCell instanceof EditorCell_Collection) {
      EditorCell_Collection collection = (EditorCell_Collection) rootCell;
      for (jetbrains.mps.openapi.editor.cells.EditorCell child : collection) {
        jetbrains.mps.openapi.editor.cells.EditorCell result = findNodeCellWithRole(child, role, node);
        if (result != null) {
          return result;
        }
      }
    }
    return null;
  }

  @Override
  public EditorCell findCellWithId(final SNode node, final @NotNull String id) {
    final EditorCell bigCell = findNodeCell(node);

    if (bigCell == null) {
      return null;
    }

    final jetbrains.mps.openapi.editor.cells.EditorCell[] result = new jetbrains.mps.openapi.editor.cells.EditorCell[]{null};
    myRepository.getModelAccess().runReadAction(() -> result[0] = findCellWithIdWithingBigCell(bigCell, id, node));

    return (EditorCell) result[0];
  }

  private jetbrains.mps.openapi.editor.cells.EditorCell findCellWithIdWithingBigCell(jetbrains.mps.openapi.editor.cells.EditorCell root, String id,
                                                                                     SNode node) {
    if (id == null) {
      return null;
    }

    if (id.equals(root.getCellId()) && root.getSNode() == node) {
      return root;
    }

    if (root instanceof jetbrains.mps.openapi.editor.cells.EditorCell_Collection) {
      for (jetbrains.mps.openapi.editor.cells.EditorCell child : ((jetbrains.mps.openapi.editor.cells.EditorCell_Collection) root)) {
        SNode childNode = child.getSNode();
        if (childNode == node || (childNode != null && AttributeOperations.isAttribute(childNode) && childNode.getParent() == node)) {
          jetbrains.mps.openapi.editor.cells.EditorCell result = findCellWithIdWithingBigCell(child, id, node);
          if (result != null) {
            return result;
          }
        }
      }
    }

    return null;
  }

  @Override
  public void rebuildEditorContent() {
    assertInEDT();
    getUpdater().update();
    relayout();
  }

  protected void assertInEDT() {
    LOG.assertLog(ThreadUtils.isInEDT(), "You should do this in EDT");
  }

  private void fireEditorWillBeDisposed() {
    for (EditorDisposeListener listener : new ArrayList<>(myDisposeListeners)) {
      listener.editorWillBeDisposed(this);
    }
    myDisposeListeners.clear();
  }

  /**
   * Adds a listener to be called when this instance is disposed. It is not necessary to remove a listener when it has been called since the collection of
   * listeners is cleared during the disposal.
   *
   * @param listener the listener to add
   */
  public void addDisposeListener(@NotNull EditorDisposeListener listener) {
    if (!myDisposeListeners.contains(listener)) {
      myDisposeListeners.add(listener);
    }
  }

  /**
   * Removes a listener so that it is no longer called when this instance is disposed. It is not necessary to remove a listener when it has been called since
   * the collection of listeners is cleared during the disposal.
   *
   * @param listener the listener to remove
   */
  public void removeDisposeListener(@NotNull EditorDisposeListener listener) {
    myDisposeListeners.remove(listener);
  }

  public jetbrains.mps.openapi.editor.cells.EditorCell findCellWeak(int x, int y) {
    jetbrains.mps.openapi.editor.cells.EditorCell cell = myRootCell.findLeaf(x, y);
    if (cell == null) {
      cell = myRootCell.findNearestLeafOnLine(x, y, Condition.TRUE_CONDITION);
    }
    return cell;
  }

  private void processMousePressed(MouseEvent mouseEvent) {
    requestFocus();
    processCoordSelection(mouseEvent);

    if (mouseEvent.getButton() == MouseEvent.BUTTON2) {
      goByCurrentReference();
    }

    boolean ctrlDown = com.intellij.openapi.util.SystemInfo.isMac ? mouseEvent.isMetaDown() : mouseEvent.isControlDown();
    if (ctrlDown) {
      if (mouseEvent.isAltDown()) {
        showCellError();
      } else {
        jetbrains.mps.openapi.editor.cells.EditorCell selectedCell = getSelectedCell();
        if (selectedCell != null) {
          String url = selectedCell.getStyle().get(StyleAttributes.URL);
          if (url != null) {
            if (!url.startsWith(URLUtil.HTTP_PROTOCOL)) {
              url = VirtualFileManager.constructUrl(URLUtil.HTTP_PROTOCOL, url);
            }
            BrowserUtil.browse(url);
          } else {
            goByCurrentReference();
          }
        }
      }
    }
  }

  public void goByCurrentReference() {
    final DataContext dataContext = DataManager.getInstance().getDataContext(this);
    getModelAccess().executeCommand(new EditorCommand(getCommandContext()) {
      @Override
      protected void doExecute() {
        AnAction action = ActionManager.getInstance().getAction(MPSActions.EDITOR_GOTO_DECLARATION);
        if (action != null) {
          AnActionEvent event = ActionUtils.createEvent(ActionPlaces.EDITOR_POPUP, dataContext);
          ActionUtils.updateAndPerformAction(action, event);
        }
      }
    });
  }

  @NotNull
  private org.jetbrains.mps.openapi.module.ModelAccess getModelAccess() {
    return myRepository.getModelAccess();
  }

  public void showCellError() {
    final jetbrains.mps.openapi.editor.cells.EditorCell selectedCell = getSelectedCell();
    if (selectedCell != null) {
      final HighlighterMessage message = getHighlighterMessageFor(selectedCell);
      MPSErrorDialog.showCellErrorDialog(getCurrentProject(), SwingUtilities.windowForComponent(EditorComponent.this), message);
    }
  }

  public void addLeftMarginPressListener(LeftMarginMouseListener listener) {
    myLeftMarginPressListeners.add(listener);
  }

  public void removeLeftMarginPressListener(LeftMarginMouseListener listener) {
    myLeftMarginPressListeners.remove(listener);
  }

  public List<LeftMarginMouseListener> getLeftMarginPressListeners() {
    return Collections.unmodifiableList(myLeftMarginPressListeners);
  }

  public void clearLeftMarginListeners() {
    myLeftMarginPressListeners.clear();
  }

  private void processCoordSelection(MouseEvent mouseEvent) {
    jetbrains.mps.openapi.editor.cells.EditorCell newSelectedCell = myRootCell.findLeaf(mouseEvent.getX(), mouseEvent.getY());
    if (newSelectedCell != null && CellTraversalUtil.getFoldedParent(newSelectedCell) != null) {
      // mouse was pressed on a cell representing folded collection
      return;
    }
    if (newSelectedCell == null || !newSelectedCell.isSelectable()) {
      newSelectedCell = myRootCell.findNearestLeafOnLine(mouseEvent.getX(), mouseEvent.getY(), jetbrains.mps.openapi.editor.cells.CellConditions.SELECTABLE);
    }

    if (newSelectedCell != null && (mouseEvent.getButton() != MouseEvent.BUTTON3 || !isUnderSelection(getSelectionManager().getSelection(), newSelectedCell))) {
      mySelectionManager.setSelection(newSelectedCell);
      ((EditorCell) newSelectedCell).processMousePressed(mouseEvent);
    }
  }

  private boolean isUnderSelection(Selection selection, jetbrains.mps.openapi.editor.cells.EditorCell cell) {
    if (selection == null) {
      return false;
    }
    for (jetbrains.mps.openapi.editor.cells.EditorCell selectedCell : selection.getSelectedCells()) {
      if (CellTraversalUtil.isAncestorOrEquals(selectedCell, cell)) {
        return true;
      }
    }
    return false;
  }

  public void clearSelectionStack() {
    getSelectionManager().clearSelection();
  }

  public void pushSelection(jetbrains.mps.openapi.editor.cells.EditorCell cell) {
    getSelectionManager().pushSelection(getSelectionManager().createSelection(cell));
  }

//  public EditorCell popSelection() {
//    Selection selection = getSelectionManager().popSelection();
//    return selection instanceof SingularSelection ? ((SingularSelection) selection).getEditorCell() : null;
//  }

  public jetbrains.mps.openapi.editor.cells.EditorCell peekSelection() {
    return getSelectedCell();
  }

  public boolean selectionStackContains(EditorCell cell) {
    for (Selection nextSelection : getSelectionManager().getSelectionStackIterable()) {
      if (nextSelection instanceof SingularSelection) {
        if (((SingularSelection) nextSelection).getEditorCell().equals(cell)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public final void changeSelection(@NotNull jetbrains.mps.openapi.editor.cells.EditorCell newSelectedCell) {
    mySelectionManager.setSelection(newSelectedCell);
    showCellInViewPort(newSelectedCell);
  }

  // TODO: replace this method with selection listener
  private void showCellInViewPort(@NotNull jetbrains.mps.openapi.editor.cells.EditorCell newSelectedCell) {
    if (!hasUI()) {
      return;
    }
    if (getVisibleRect().isEmpty()) {
      final JViewport viewport = getViewport();
      viewport.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
          if (!getVisibleRect().isEmpty()) {
            viewport.removeChangeListener(this);
            if (getSelectedCell() != null) {
              scrollToCell(getSelectedCell());
            }
          }
        }
      });
    } else {
      scrollToCell(newSelectedCell);
    }
  }

  @Override
  public void scrollToNode(SNode node) {
    EditorCell cell = findNodeCell(node);
    if (cell != null) {
      scrollToCell(cell);
    }
  }

  public void ensureSelectionVisible() {
    if (getSelectedCell() == null) {
      return;
    }
    scrollToCell(getSelectedCell());
  }

  @Override
  public void scrollToCell(@NotNull jetbrains.mps.openapi.editor.cells.EditorCell cell) {
    if (!hasUI() || getVisibleRect().isEmpty()) {
      return;
    }

    int viewportWidth = getViewport().getWidth();

    int x0;
    int width;

    if (cell instanceof EditorCell_Label) {
      EditorCell_Label cellLabel = (EditorCell_Label) cell;
      int caretX = cellLabel.getCaretX();
      int charWidth = cellLabel.getCharWidth();
      width = 4 * charWidth;
      x0 = caretX - 2 * charWidth;
    } else if (getDeepestSelectedCell() instanceof EditorCell_Label && cell.getWidth() > viewportWidth) {
      EditorCell_Label cellLabel = (EditorCell_Label) getDeepestSelectedCell();
      int caretX = cellLabel.getCaretX();
      int charWidth = cellLabel.getCharWidth();
      x0 = Math.max(cell.getX(), caretX + 2 * charWidth - viewportWidth);
      width = viewportWidth;
    } else {
      x0 = cell.getX();
      width = cell.getWidth();
    }

    Rectangle visibleRect = getVisibleRect();
    Rectangle rectangle = new Rectangle(x0, visibleRect.y, width, visibleRect.height);
    if (!rectangle.isEmpty()) {
      boolean adjustHorizontally = !visibleRect.contains(rectangle);
      if (adjustHorizontally) {
        if (width <= viewportWidth) {
          int x1 = Math.max(0, x0 + width - viewportWidth);
          scrollToRectIfNotVisible(
              expandRectangleOneLine(
                  new Rectangle(x1, cell.getY(), x0 - x1 + width, cell.getHeight()
                  )
              )
          );
        } else {
          scrollToRectIfNotVisible(
              expandRectangleOneLine(
                  new Rectangle(x0 - SCROLL_GAP, cell.getY(), viewportWidth + SCROLL_GAP, cell.getHeight()
                  )
              )
          );
        }
      } else {
        scrollToRectIfNotVisible(
            expandRectangleOneLine(
                new Rectangle(x0, cell.getY(), width, cell.getHeight()
                )
            )
        );
      }
    }
  }

  private void scrollToRectIfNotVisible(Rectangle rect) {
    if (getVisibleRect().contains(rect)) {
      return;
    }

    scrollRectToVisible(rect);
  }

  private Rectangle expandRectangleOneLine(Rectangle r) {
    Font defaultFont = EditorSettings.getInstance().getDefaultEditorFont();
    FontMetrics fontMetrics = getFontMetrics(defaultFont);
    int height = fontMetrics.getHeight();
    return new Rectangle(r.x, r.y - height, r.width, r.height + 2 * height);
  }

  @Override
  protected void paintComponent(Graphics gg) {
    EditorSettings setting = EditorSettings.getInstance();
    Graphics2D g = (Graphics2D) gg;

    turnOnAliasingIfPossible(g);

    g.setColor(getBackground());
    Rectangle bounds = g.getClipBounds();

    g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

    jetbrains.mps.openapi.editor.cells.EditorCell deepestCell = getDeepestSelectedCell();
    if (deepestCell instanceof EditorCell_Label && ((EditorCell) deepestCell).isInClipRegion(g)) {
      EditorCell_Label label = (EditorCell_Label) deepestCell;

      g.setColor(setting.getCaretRowColor());
      g.fillRect(0, deepestCell.getY(), getWidth(),
                 deepestCell.getHeight() - deepestCell.getTopInset() - deepestCell.getBottomInset());

      g.setColor(EditorColorsManager.getInstance().getGlobalScheme().getAttributes(EditorColors.IDENTIFIER_UNDER_CARET_ATTRIBUTES).getBackgroundColor());
      g.fillRect(deepestCell.getX() + label.getLeftInset(),
                 deepestCell.getY(),
                 deepestCell.getWidth() - label.getLeftInset() - label.getRightInset(),
                 deepestCell.getHeight() - deepestCell.getTopInset() - deepestCell.getBottomInset());

    }

    List<AdditionalPainter> additionalPainters = getAdditionalPainters();
    for (AdditionalPainter additionalPainter : additionalPainters) {
      if (additionalPainter.paintsBackground()) {
        additionalPainter.paintBackground(g, this);
      }
    }

    if (isDisposed()) {
      return;
    }
    myRootCell.relayout();

    if (myRootCell.isInClipRegion(g)) {
      g.setColor(EditorColorsManager.getInstance().getGlobalScheme().getColor(EditorColors.RIGHT_MARGIN_COLOR));
      int boundPosition = myRootCell.getX() + setting.getVerticalBoundWidth();
      g.drawLine(boundPosition, 0, boundPosition, getHeight());

      myRootCell.paint(g);
    }

    for (AdditionalPainter additionalPainter : additionalPainters) {
      if (additionalPainter.paintsAbove()) {
        additionalPainter.paint(g, this);
      }
    }

  }

  Dimension getPreferredComponentSize() {
    return isDisposed() ? new Dimension(0, 0) : new Dimension(myRootCell.getWidth() + myShiftX + 10, myRootCell.getHeight() + myShiftY + 10);
  }

  @Override
  public Dimension getPreferredScrollableViewportSize() {
    return getPreferredSize();
  }

  @Override
  public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
    if (orientation == SwingConstants.VERTICAL) {
      return 20;
    } else { // if orientation == SwingConstants.HORIZONTAL
      return 20;
    }
  }

  @Override
  public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
    return visibleRect.height;
  }

  @Override
  public boolean getScrollableTracksViewportWidth() {
    assert hasUI();
    return myScrollPane.getViewport().getWidth() > getPreferredSize().width;
  }

  @Override
  public boolean getScrollableTracksViewportHeight() {
    assert hasUI();
    return myScrollPane.getViewport().getHeight() > getPreferredSize().height;
  }

  @Override
  public jetbrains.mps.openapi.editor.cells.EditorCell getDeepestSelectedCell() {
    if (isDisposed()) {
      return null;
    }
    Selection deepestSelection = getSelectionManager().getDeepestSelection();
    return deepestSelection instanceof SingularSelection ? ((SingularSelection) deepestSelection).getEditorCell() : null;
  }

  @Nullable
  public jetbrains.mps.openapi.editor.cells.EditorCell getSelectedCell() {
    if (isDisposed()) {
      return null;
    }
    Selection currentSelection = getSelectionManager().getSelection();
    return currentSelection instanceof SingularSelection ? ((SingularSelection) currentSelection).getEditorCell() : null;
  }

  @NotNull
  public SelectionManager getSelectionManager() {
    return mySelectionManager;
  }

  @NotNull
  @Override
  public Updater getUpdater() {
    return myUpdater;
  }

  public KeyboardHandler peekKeyboardHandler() {
    return myKbdHandlersStack.peek();
  }

  public KeyboardHandler popKeyboardHandler() {
    return myKbdHandlersStack.pop();
  }

  public void pushKeyboardHandler(KeyboardHandler kbdHandler) {
    myKbdHandlersStack.push(kbdHandler);
  }

  public void setMouseEventHandler(MouseListener l) {
    assert myMouseEventHandler == null : "Mouse event handler was already specified";
    addMouseListener(myMouseEventHandler = l);
  }

  public void removeMouseEventHandler() {
    assert myMouseEventHandler != null : "Mouse event handler was was not specified";
    removeMouseListener(myMouseEventHandler);
    myMouseEventHandler = null;
  }

  private boolean areMouseEventsBlocked() {
    return myMouseEventHandler != null;
  }

  /**
   * @deprecated use {@link EditorComponent#getTypecheckingSession()} to launch typechecking reusing the cached state.
   * @return
   */
  @Deprecated
  @NotNull
  public ITypeContextOwner getTypecheckingContextOwner() {
    return this;
  }

  public TypecheckingSession getTypecheckingSession() {
    return myTypecheckingSession;
  }

  /**
   * Returns false iff the containing root has been changed as a result of this method call.
   */
  protected boolean updateContainingRoot(SNode node) {
    return myNode != node;
  }

  public SNode getNodeForTypechecking() {
    return getEditedNode();
  }

  public void sendKeyEvent(KeyEvent keyEvent) {
    if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
      processKeyPressed(keyEvent);
    } else if (keyEvent.getID() == KeyEvent.KEY_RELEASED) {
      processKeyReleased(keyEvent);
    }
  }

  @Override
  public void update() {
    final jetbrains.mps.project.Project p = getCurrentProject();
    final Highlighter highlighter = p == null ? null : p.getComponent(Highlighter.class);
    getModelAccess().runReadAction(() -> {
      //TODO: check if it's necessary to clear updater caches here?..
      rebuildAfterReloadModel();
      if (highlighter != null) {
        highlighter.resetCheckedStateInBackground(EditorComponent.this);
      }
      rebuildEditorContent();
    });
  }

  public void processKeyPressed(final KeyEvent keyEvent) {
    if (keyEvent.isConsumed() || isDisposed()) {
      return;
    }

    // hardcoded "update" action
    if (keyEvent.getKeyCode() == KeyEvent.VK_F5 && noKeysDown(keyEvent)) {
      //this lock should be obtained before the following read action to avoid deadlock
      update();
      keyEvent.consume();
      return;
    }

    if (keyEvent.getKeyCode() == KeyEvent.VK_F11 && noKeysDown(keyEvent)) {
      relayout();
      keyEvent.consume();
      return;
    }

    if (isKeyboardHandlerProcessingEnabled(keyEvent) && peekKeyboardHandler().processKeyPressed(getEditorContext(), keyEvent)) {
      keyEvent.consume();
    }
    repaintExternalComponent();
  }

  public void processKeyReleased(final KeyEvent keyEvent) {
    if (keyEvent.isConsumed()) {
      return;
    }

    if (isKeyboardHandlerProcessingEnabled(keyEvent) && peekKeyboardHandler().processKeyReleased(getEditorContext(), keyEvent)) {
      keyEvent.consume();
    }
    repaintExternalComponent();
  }

  public void processKeyTyped(final KeyEvent keyEvent) {
    if (keyEvent.isConsumed()) {
      return;
    }

    if (isKeyboardHandlerProcessingEnabled(keyEvent) && peekKeyboardHandler().processKeyTyped(getEditorContext(), keyEvent)) {
      keyEvent.consume();
    }
    repaintExternalComponent();
  }

  private boolean isKeyboardHandlerProcessingEnabled(KeyEvent keyEvent) {
    if (!ReadOnlyUtil.isSelectionReadOnlyInEditor(this)) {
      return true;
    }
    CellActionType actionType = getActionType(keyEvent, getEditorContext());
    if (actionType != null) {
      switch (actionType) {
        case SELECT_LEFT:
        case SELECT_RIGHT:
        case SELECT_HOME:
        case SELECT_END:
        case PAGE_UP:
        case PAGE_DOWN:
        case NEXT:
        case PREV:
          return true;
      }
    }
    return false;
  }

  public CommandContext getCommandContext() {
    return myCommandContext;
  }

  private CommandContext getNoSelectionProcessingCommandContext() {
    // Hiding command start/stop notification.
    return new CommandContextWrapper(getCommandContext()) {
      @Override
      public void commandStarted() {
      }

      @Override
      public void commandFinished() {
      }
    };
  }

  <T> T runRead(final Computable<T> c) {
    final ComputeRunnable<T> r = new ComputeRunnable<>(c);
    getModelAccess().runReadAction(r);
    return r.getResult();
  }

  /**
   * @deprecated editor component does not always correspond to a project!
   */
  @Nullable
  @Deprecated
  @ToRemove(version = 3.5)
  protected final jetbrains.mps.project.Project getCurrentProject() {
    // there's no need in MPSProject, there's just no key for generic MPS project in MPSCommonDataKeys.
    final MPSProject p = MPSCommonDataKeys.MPS_PROJECT.getData(DataManager.getInstance().getDataContext(this));
    return p != null ? p : ProjectHelper.getProject(myRepository);
  }

  public boolean activateNodeSubstituteChooser(jetbrains.mps.openapi.editor.cells.EditorCell editorCell, boolean resetPattern) {
    return activateNodeSubstituteChooser(editorCell, resetPattern, false);
  }

  public boolean activateNodeSubstituteChooser(jetbrains.mps.openapi.editor.cells.EditorCell editorCell, boolean resetPattern, boolean isSmart) {
    if (myNodeSubstituteChooser.isVisible()) {
      return true;
      //todo: rebuild menu if smartness changed
    }

    // try to obtain substitute info
    SubstituteInfo substituteInfo = null;
    if (editorCell != null) {
      substituteInfo = editorCell.getSubstituteInfo();
    }

    return activateNodeSubstituteChooser(editorCell, substituteInfo, resetPattern, isSmart);
  }

  public boolean activateNodeSubstituteChooser(jetbrains.mps.openapi.editor.cells.EditorCell editorCell, SubstituteInfo substituteInfo,
                                               boolean resetPattern) {
    return activateNodeSubstituteChooser(editorCell, substituteInfo, resetPattern, false);
  }

  public boolean activateNodeSubstituteChooser(jetbrains.mps.openapi.editor.cells.EditorCell editorCell, SubstituteInfo substituteInfo, boolean resetPattern,
                                               boolean isSmart) {
    if (editorCell == null || substituteInfo == null) {
      return false;
    }
    NodeSubstitutePatternEditor patternEditor = ((EditorCell) editorCell).createSubstitutePatternEditor();
    if (resetPattern) {
      patternEditor.toggleReplaceMode();
    }
    NodeSubstituteChooserHandler substituteChooserHandler = new NodeSubstituteChooserHandler(editorCell, this, substituteInfo, patternEditor, isSmart);
    if (!substituteChooserHandler.tryToSubstituteImmediately()){
      substituteChooserHandler.showNodeSubstituteChooser(myNodeSubstituteChooser);
    }
    return true;
  }


  private void deactivateSubstituteChooser() {
    myNodeSubstituteChooser.setVisible(false);
  }

  public NodeSubstituteChooser getNodeSubstituteChooser() {
    return myNodeSubstituteChooser;
  }

  void setNodeInformationDialog(NodeInformationDialog dialog) {
    myNodeInformationDialog = dialog;
  }

  public boolean hasNodeInformationDialog() {
    return myNodeInformationDialog != null;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Selection selection = getSelectionManager().getSelection();
    if (selection != null) {
      ((SelectionInternal) selection).paintSelection((Graphics2D) g);
    }
  }

  public Set<SNode> getNodesCellDependOn(jetbrains.mps.openapi.editor.cells.EditorCell cell) {
    return myUpdater.getRelatedNodes(cell);
  }

  public Set<SNodeReference> getCopyOfRefTargetsCellDependsOn(jetbrains.mps.openapi.editor.cells.EditorCell cell) {
    return myUpdater.getRelatedRefTargets(cell);
  }

  @Nullable
  public EditorCell getBigValidCellForNode(SNode node) {
    EditorCell result = findNodeCell(node);
    if (isValid(result)) {
      return result;
    }
    return null;
  }

  public boolean isValid(jetbrains.mps.openapi.editor.cells.EditorCell cell) {
    if (cell == null) {
      return false;
    }
    return ((EditorCell_Basic) cell).isInTree() && cell.getEditorComponent() == this;
  }

  public jetbrains.mps.openapi.editor.cells.EditorCell changeSelectionWRTFocusPolicy(@NotNull jetbrains.mps.openapi.editor.cells.EditorCell cell) {
    jetbrains.mps.openapi.editor.cells.EditorCell focusPolicyCell = FocusPolicyUtil.findFocusedCell(cell);
    jetbrains.mps.openapi.editor.cells.EditorCell toSelect;
    if (focusPolicyCell == null || (focusPolicyCell == cell && !FocusPolicyUtil.hasFocusPolicy(focusPolicyCell))) {
      toSelect = CellFinderUtil.findChildByManyFinders(cell, Finder.FIRST_ERROR, Finder.FIRST_EDITABLE, Finder.FIRST_SELECTABLE_LEAF);
    } else {
      toSelect = focusPolicyCell;
    }
    if (toSelect == null) {
      toSelect = cell;
    }
    changeSelection(toSelect);

    if (toSelect instanceof EditorCell_Label) {
      EditorCell_Label label = (EditorCell_Label) toSelect;
      jetbrains.mps.editor.runtime.style.CaretPosition defaultCaretPosition = label.getStyle().get(StyleAttributes.DEFAULT_CARET_POSITION);
      if (defaultCaretPosition != null) {
        if (defaultCaretPosition == jetbrains.mps.editor.runtime.style.CaretPosition.FIRST) {
          label.home();
        }
        if (defaultCaretPosition == jetbrains.mps.editor.runtime.style.CaretPosition.LAST) {
          label.end();
        }
      } else if (!toSelect.isErrorState()) {
        label.end();
      }
    }

    return toSelect;
  }

  private void setEditorContext(@Nullable SModel model, @NotNull SRepository repository) {
    if (myEditorContext != null && myEditorContext.getModel() == model && myEditorContext.getRepository() == repository) {
      myEditorContext.reset();
      return;
    }
    myEditorContext = createEditorContext(model, repository);
  }

  /**
   * This method is called from the constructor, so you cannot use local variables and any other
   * EditorComponent state here!
   *
   * @param model
   * @param repository
   */
  @NotNull
  protected EditorContext createEditorContext(@Nullable SModel model, @NotNull SRepository repository) {
    return new EditorContext(this, model, repository, getEditorConfiguration(), createContextAssistantManager(repository));
  }

  protected ContextAssistantManager createContextAssistantManager(SRepository repository) {
    return DefaultContextAssistantManager.newInstance(this, repository);
  }

  @Override
  public boolean isReadOnly() {
    return myReadOnly;
  }

  public void setPopupMenuEnabled(boolean popupMenuEnabled) {
    myPopupMenuEnabled = popupMenuEnabled;
  }

  @Override
  @Nullable
  public Object getData(@NonNls String dataId) {
    if (isDisposed()) {
      return null;
    }

    boolean isInSearchPanel = mySearchPanel != null && mySearchPanel.isVisible() && mySearchPanel.isTextFieldFocused();
    boolean isInSubstituteChooser = myNodeSubstituteChooser.isVisible();

    //MPSDK
    if (dataId.equals(MPSCommonDataKeys.NODE.getName())) {
      return isInSubstituteChooser || isInSearchPanel ? null : getSelectedNode();
    }
    if (dataId.equals(MPSCommonDataKeys.NODES.getName())) {
      return isInSubstituteChooser || isInSearchPanel ? null : getSelectedNodes();
    }
    if (dataId.equals(MPSEditorDataKeys.CONTEXT_MODEL.getName())) {
      return runRead(() -> {
        SNode node = getRootCell().getSNode();
        if (node == null) {
          return null;
        }
        SModel model = node.getModel();
        if (model == null) {
          return null; //removed model
        }
        return model;
      });
    }
    if (dataId.equals(MPSEditorDataKeys.CONTEXT_MODULE.getName())) {
      return runRead(() -> {
        SModel model = MPSEditorDataKeys.CONTEXT_MODEL.getData(this);
        return model == null ? null : model.getModule();
      });
    }
    if (dataId.equals(MPSEditorDataKeys.EDITOR_CONTEXT.getName())) {
      return createEditorContextForActions();
    }
    if (dataId.equals(MPSEditorDataKeys.EDITOR_CELL.getName())) {
      return isInSubstituteChooser || isInSearchPanel ? null : getSelectedCell();
    }
    if (dataId.equals(MPSEditorDataKeys.EDITOR_COMPONENT.getName())) {
      return isInSearchPanel ? null : this;
    }
    if (dataId.equals(PlatformDataKeys.SELECTED_ITEM.getName())) {
      return isInSubstituteChooser ? myNodeSubstituteChooser.getCurrentSubstituteAction() : null;
    }
    if (dataId.equals(MPSCommonDataKeys.PLACE.getName())) {
      return ActionPlace.EDITOR;
    }


    //PDK
    if (dataId.equals(PlatformDataKeys.CUT_PROVIDER.getName())) {
      return new MyCutProvider();
    }
    if (dataId.equals(PlatformDataKeys.COPY_PROVIDER.getName())) {
      return new MyCopyProvider();
    }
    if (dataId.equals(PlatformDataKeys.PASTE_PROVIDER.getName()) && (isFocusOwner() || mySearchPanel == null || !mySearchPanel.isVisible())) {
      return new MyPasteProvider();
    }

    if (dataId.equals(SelectInContext.DATA_KEY.getName())) {
      ProjectViewSelectInProvider selectInHelper =
          ApplicationManager.getApplication() == null ? null : ApplicationManager.getApplication().getComponent(ProjectViewSelectInProvider.class);
      if (selectInHelper == null) {
        return null;
      }
      return selectInHelper.getContext(getCurrentProject(), myNodePointer);
    }

    //not found
    return null;
  }

  private void commitAllCellValues() {
    final List<EditorCell_Property> cellsToCommit = getCellsToCommit();
    if (cellsToCommit.isEmpty()) {
      return;
    }

    getModelAccess().executeCommand(new EditorCommandAdapter(() -> doCommitAll(cellsToCommit), getNoSelectionProcessingCommandContext()));
  }

  private void setDefaultSelection() {
    if (getSelectionManager().getSelection() != null) {
      return;
    }

    EditorCell rootCell = getRootCell();
    if (rootCell instanceof EditorCell_Collection) {
      jetbrains.mps.openapi.editor.cells.EditorCell focusPolicyCell = FocusPolicyUtil.findFocusedCell(rootCell);
      jetbrains.mps.openapi.editor.cells.EditorCell toSelect;
      if (focusPolicyCell == null || (focusPolicyCell == rootCell && !FocusPolicyUtil.hasFocusPolicy(focusPolicyCell))) {
        toSelect = CellFinderUtil.findChildByManyFinders(rootCell, Finder.FIRST_EDITABLE, Finder.FIRST_SELECTABLE_LEAF);
      } else {
        toSelect = focusPolicyCell;
      }
      if (toSelect == null) {
        toSelect = rootCell;
      }
      changeSelection(toSelect);
      return;
    }
    if (rootCell != null && rootCell.isSelectable()) {
      changeSelection(rootCell);
    }
  }

  private void closeSubstituteChooser(Component newFocusOwner) {
    if (myNodeSubstituteChooser.getWindow() != null &&
        (myNodeSubstituteChooser.getWindow().isAncestorOf(newFocusOwner) || myNodeSubstituteChooser.getWindow() == newFocusOwner)) {
      return;
    }
    deactivateSubstituteChooser();
  }

  private void activateCaretBlinker() {
    myEditorConfiguration.caretManager.setActiveEditor(this);
  }

  private void deActivateCaretBlinker() {
    myEditorConfiguration.caretManager.unsetActiveEditor(this);
  }

  private List<EditorCell_Property> getCellsToCommit() {
    List<EditorCell_Property> cells = new ArrayList<>();

    for (EditorCell_Property cell : getCellTracker().getTransactionalCells()) {
      if (cell.hasUncommittedValue()) {
        cells.add(cell);
      }
    }

    return cells;
  }

  private void doCommitAll(List<EditorCell_Property> cells) {
    for (EditorCell_Property cell : cells) {
      cell.commit();
    }
  }

  /**
   * It's possible that associated module was already removed from MPSModuleRepository (for example - transient models
   * modules are currently removed from MPSModuleRepository before next code generation session). In this case currently
   * open editor should be closed as a result of another notification processing. We need to suppress editor update
   * process in this case because an editor is not in valid state right now.
   */
  private boolean isModuleDisposed() {
    // TODO review
    return false; // myOperationContext != null && myOperationContext.getModule() == null;
  }

  private boolean isProjectDisposed() {
    final jetbrains.mps.project.Project p = getCurrentProject();
    // XXX NOTE, we check the project is there, i.e. missing project is not treated as disposed. Is it right?
    return p != null && p.isDisposed();
  }

  private boolean isNodeDisposed() {
    SNode node = getEditedNode();
    return node != null && !SNodeUtil.isAccessible(node, myEditorContext.getRepository());
  }

  public CellTracker getCellTracker() {
    return myCellTracker;
  }

  public BracesHighlighter getBracesHighlighter() {
    return myBracesHighlighter;
  }

  public void rebuildAfterReloadModel() {
    if (myTypecheckingSession != null) {
      myTypecheckingSession.release();
      myTypecheckingSession = null;
    }
    if (myNodePointer != null) {
      myNode = myNodePointer.resolve(getRepository());
      myEditorContext = createEditorContext(myNode == null ? null : myNode.getModel(), myRepository);
      myUpdater.clearExplicitHints();
    }
    myCommandContext.updateContextNode();
    if (myNode != null) {
      myTypecheckingSession = TypecheckingFacade
                                  .getFromContext()
                                  .requestNewSession(TypecheckingSession.Flags.forRoot(getNodeForTypechecking()).incremental());
    }
  }

  private static class MyBaseAction extends BaseAction implements DumbAware {
    private final KeyMapAction myAction;
    private final EditorContext myEditorContext;

    public MyBaseAction(KeyMapAction action, EditorContext editorContext) {
      super("" + action.getDescriptionText());
      myAction = action;
      myEditorContext = editorContext;
      String keyStrokeString = action.getKeyStroke();
      if (keyStrokeString == null || keyStrokeString.length() == 0) {
        return;
      }
      KeyStroke keyStroke = KeyStroke.getKeyStroke(keyStrokeString);
      if (keyStroke == null) {
        LOG.error("Invalid keystroke (" + keyStrokeString + ") specified for the action: " + action.getClass().getName());
        return;
      }
      KeyboardShortcut shortcut = new KeyboardShortcut(keyStroke, null);
      KeymapManager.getInstance().getKeymap(KeymapManager.DEFAULT_IDEA_KEYMAP).addShortcut(getActionId(), shortcut);
      setExecuteOutsideCommand(true);
    }

    @Override
    protected void doExecute(AnActionEvent e, Map<String, Object> _params) {
      myEditorContext.getRepository().getModelAccess().executeCommand(new EditorCommand(myEditorContext) {
        @Override
        protected void doExecute() {
          try {
            myAction.execute(myEditorContext);
          } catch (Throwable t) {
            LOG.error(t);
          }
        }
      });
    }
  }

  public interface EditorDisposeListener {
    void editorWillBeDisposed(@NotNull EditorComponent component);
  }

  public void repaint(@NotNull jetbrains.mps.openapi.editor.cells.EditorCell cell) {
    // The +1 for height takes into account decorations such as selection or border, which may currently be drawn outside the cell.
    repaint(0, cell.getY(), getWidth(), cell.getHeight() + 1);
  }

  @Override
  public ActionHandler getActionHandler() {
    return myActionHandler;
  }

  /**
   * Return true if UI focus "within" this editor component. Means: owned by this component or any child-components
   * (in case of component cells displayed inside this editor). Context assistant is a special case: if it is focused,
   * the editor is considered inactive.
   *
   * @return true if the focus is inside this EditorComponent
   */
  public boolean isActive() {
    if (isContextAssistantFocused()) {
      return false;
    }
    if (isFocusOwner()) {
      return true;
    }

    Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
    return isAncestorOf(focusOwner);
  }

  private boolean isContextAssistantFocused() {
    ContextAssistant activeAssistant = myEditorContext.getContextAssistantManager().getActiveAssistant();
    return activeAssistant != null && activeAssistant.hasFocus();
  }

  @NotNull
  public EditorHighlighter getHighlighter() {
    return myHighlighter;
  }

  @NotNull
  public EditorComponentFocusTracker getFocusTracker() {
    return myFocusTracker;
  }

  private class ReferenceUnderliner {
    private EditorCell myLastReferenceCell;

    private ReferenceUnderliner() {
      addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
          if (e.getKeyCode() == getKeyCode()) {
            setControlOver();
          }
        }

        @Override
        public void keyReleased(KeyEvent e) {
          if (e.getKeyCode() == getKeyCode()) {
            clearControlOver();
          }
        }

        private int getKeyCode() {
          return com.intellij.openapi.util.SystemInfo.isMac ? KeyEvent.VK_META : KeyEvent.VK_CONTROL;
        }
      });
      addMouseMotionListener(new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
          if (!myEditorContext.getNodeEditorComponent().isFocusOwner()) {
            return;
          }
          if (isDisposed()) {
            myLastReferenceCell = null;
            return;
          }

          clearControlOver();
          if (!(com.intellij.openapi.util.SystemInfo.isMac ? e.isMetaDown() : e.isControlDown())) {
            myLastReferenceCell = null;
            return;
          }

          final jetbrains.mps.openapi.editor.cells.EditorCell editorCell = myRootCell.findLeaf(e.getX(), e.getY());
          if (editorCell == null) {
            myLastReferenceCell = null;
            return;
          }
          SNode snodeWRTReference = runRead(() -> isInvalid() ? null : APICellAdapter.getSNodeWRTReference(editorCell));
          String url = editorCell.getStyle().get(StyleAttributes.URL);
          if (editorCell.getSNode() == snodeWRTReference && url == null) {
            myLastReferenceCell = null;
            return;
          }
          myLastReferenceCell = (EditorCell) editorCell;

          setControlOver();
        }
      });
      addFocusListener(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
          clearControlOver();
          myLastReferenceCell = null;
        }
      });
    }

    private void clearControlOver() {
      if (myLastReferenceCell != null) {
        myLastReferenceCell.getStyle().set(StyleAttributes.CONTROL_OVERED_REFERENCE, false);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        repaintExternalComponent();
      }
    }

    private void setControlOver() {
      if (myLastReferenceCell != null) {
        myLastReferenceCell.getStyle().set(StyleAttributes.CONTROL_OVERED_REFERENCE, true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        repaintExternalComponent();
      }
    }
  }

  private class MyCutProvider implements CutProvider {
    @Override
    public void performCut(@NotNull final DataContext dataContext) {
      getModelAccess().executeCommandInEDT(new EditorCommand(getCommandContext()) {
        @Override
        protected void doExecute() {
          if (isInvalid() || !isCutEnabled(dataContext)) {
            return;
          }
          jetbrains.mps.openapi.editor.cells.EditorCell selectedCell = getSelectedCell();
          if (selectedCell != null) {
            myActionHandler.executeAction(selectedCell, CellActionType.CUT);
          } else {
            getSelectionManager().getSelection().executeAction(CellActionType.CUT);
          }
        }
      });
    }

    @Override
    public boolean isCutEnabled(@NotNull DataContext dataContext) {
      return !(isDisposed() || isInvalidLightweight() || getSelectionManager().getSelection() == null ||
               ReadOnlyUtil.canDeleteNodes(EditorComponent.this, getSelectedNodes()));
    }

    @Override
    public boolean isCutVisible(@NotNull DataContext dataContext) {
      return true;
    }
  }

  private class MyCopyProvider implements CopyProvider {
    @Override
    public void performCopy(@NotNull DataContext dataContext) {
      getModelAccess().executeCommandInEDT(new EditorCommand(getCommandContext()) {
        @Override
        protected void doExecute() {
          if (isDisposed() || isInvalid()) {
            return;
          }
          jetbrains.mps.openapi.editor.cells.EditorCell selectedCell = getSelectedCell();
          if (selectedCell != null) {
            myActionHandler.executeAction(selectedCell, CellActionType.COPY);
          } else {
            getSelectionManager().getSelection().executeAction(CellActionType.COPY);
          }
        }
      });
    }

    @Override
    public boolean isCopyEnabled(@NotNull DataContext dataContext) {
      return !isDisposed() && !isInvalidLightweight() && getSelectionManager().getSelection() != null;
    }

    @Override
    public boolean isCopyVisible(@NotNull DataContext dataContext) {
      return true;
    }
  }

  private class MyPasteProvider implements PasteProvider {
    @Override
    public void performPaste(@NotNull final DataContext dataContext) {
      EditorComponent.this.performPaste();
    }

    @Override
    public boolean isPastePossible(@NotNull DataContext dataContext) {
      return EditorComponent.this.isPastePossible();
    }

    @Override
    public boolean isPasteEnabled(@NotNull DataContext dataContext) {
      return true;
    }
  }

  private void performPaste() {
    getModelAccess().executeCommandInEDT(new EditorCommand(getCommandContext()) {
      @Override
      protected void doExecute() {
        if (isInvalid() || !isPastePossible()) {
          return;
        }
        jetbrains.mps.openapi.editor.cells.EditorCell selectedCell = getSelectedCell();
        if (selectedCell != null) {
          myActionHandler.executeAction(selectedCell, CellActionType.PASTE);
        } else {
          getSelectionManager().getSelection().executeAction(CellActionType.PASTE);
        }
      }
    });
  }

  private boolean isPastePossible() {
    return !(isDisposed() || isInvalidLightweight() || ReadOnlyUtil.isSelectionReadOnlyInEditor(EditorComponent.this) ||
             getSelectionManager().getSelection() == null);
  }

  @Override
  public InputMethodRequests getInputMethodRequests() {
// Uncomment at the moment https://youtrack.jetbrains.com/issue/JRE-252 is fixed
//    if (ReadOnlyUtil.isSelectionReadOnlyInEditor(this)) {
//      return null;
//    }

    hideMessageToolTip();

    if (myInputMethodRequests == null) {
      myInputMethodRequests = new InputMethodRequestsImpl(this);
    }
    return myInputMethodRequests;
  }

  /**
   * This is a copy of com.intellij.openapi.editor.impl.EditorImpl.MyScrollBar classwith some additional code
   */
  private static final Field decrButtonField;
  private static final Field incrButtonField;

  static {
    try {
      decrButtonField = BasicScrollBarUI.class.getDeclaredField("decrButton");
      decrButtonField.setAccessible(true);

      incrButtonField = BasicScrollBarUI.class.getDeclaredField("incrButton");
      incrButtonField.setAccessible(true);
    } catch (NoSuchFieldException e) {
      throw new IllegalStateException(e);
    }
  }

  class MyScrollBar extends JBScrollBar implements IdeGlassPane.TopComponent, TooltipComponent {
    @NonNls
    private static final String APPLE_LAF_AQUA_SCROLL_BAR_UI_CLASS = "apple.laf.AquaScrollBarUI";
    private ScrollBarUI myPersistentUI;

    MyScrollBar(int orientation) {
      super(orientation);
    }

    void setPersistentUI(ScrollBarUI ui) {
      myPersistentUI = ui;
      setUI(ui);
    }

    @Override
    public boolean canBePreprocessed(MouseEvent e) {
      return JBScrollPane.canBePreprocessed(e, this);
    }

    @Override
    public void setUI(ScrollBarUI ui) {
      if (myPersistentUI == null) {
        myPersistentUI = ui;
      }
      super.setUI(myPersistentUI);
    }

    /**
     * This is helper method. It returns height of the top (decrease) scroll bar
     * button. Please note, that it's possible to return real height only if scroll bar
     * is instance of BasicScrollBarUI. Otherwise it returns fake (but good enough :) )
     * value.
     */
    int getDecScrollButtonHeight() {
      ScrollBarUI barUI = getUI();
      Insets insets = getInsets();
      if (barUI instanceof ButtonlessScrollBarUI) {
        return insets.top + ((ButtonlessScrollBarUI) barUI).getDecrementButtonHeight();
      } else if (barUI instanceof BasicScrollBarUI) {
        try {
          JButton decrButtonValue = (JButton) decrButtonField.get(barUI);
          LOG.assertLog(decrButtonValue != null);
          return insets.top + decrButtonValue.getHeight();
        } catch (Exception exc) {
          throw new IllegalStateException(exc);
        }
      } else {
        return insets.top + 15;
      }
    }

    /**
     * This is helper method. It returns height of the bottom (increase) scroll bar
     * button. Please note, that it's possible to return real height only if scroll bar
     * is instance of BasicScrollBarUI. Otherwise it returns fake (but good enough :) )
     * value.
     */
    int getIncScrollButtonHeight() {
      ScrollBarUI barUI = getUI();
      Insets insets = getInsets();
      if (barUI instanceof ButtonlessScrollBarUI) {
        return insets.top + ((ButtonlessScrollBarUI) barUI).getIncrementButtonHeight();
      } else if (barUI instanceof BasicScrollBarUI) {
        try {
          JButton incrButtonValue = (JButton) incrButtonField.get(barUI);
          LOG.assertLog(incrButtonValue != null);
          return insets.bottom + incrButtonValue.getHeight();
        } catch (Exception exc) {
          throw new IllegalStateException(exc.getMessage());
        }
      } else if (APPLE_LAF_AQUA_SCROLL_BAR_UI_CLASS.equals(barUI.getClass().getName())) {
        return insets.bottom + 30;
      } else {
        return insets.bottom + 15;
      }
    }

    @Override
    public int getUnitIncrement(int direction) {
      assert hasUI();
      JViewport vp = myScrollPane.getViewport();
      Rectangle vr = vp.getViewRect();
      return getScrollableUnitIncrement(vr, SwingConstants.VERTICAL, direction);
    }

    @Override
    public int getBlockIncrement(int direction) {
      assert hasUI();
      JViewport vp = myScrollPane.getViewport();
      Rectangle vr = vp.getViewRect();
      return getScrollableBlockIncrement(vr, SwingConstants.VERTICAL, direction);
    }

    @Override
    public String getMPSTooltipText(MouseEvent mouseEvent) {
      if (getUI() instanceof TooltipComponent) {
        return ((TooltipComponent) getUI()).getMPSTooltipText(mouseEvent);
      }
      return null;
    }
  }

  /**
   * Returns deletion approver
   * It first checks if deletion approving should be used and then returns an appropriate
   * instance. If instance doesn't exist, it creates it and initializes it properly
   *
   * @return deletion approver instance
   */
  public synchronized DeletionApprover getDeletionApprover() {
    if (EditorSettings.getInstance().isUseTwoStepDeletion()) {
      if (myDeletionApprover == null) {
        myDeletionApprover = new DeletionApproverImpl(this);
        myDeletionApprover.initialize();
      }
      return myDeletionApprover;
    } else {
      return jetbrains.mps.openapi.editor.EditorComponent.super.getDeletionApprover();
    }
  }
}
