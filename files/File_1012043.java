/*
 * Copyright 2003-2017 JetBrains s.r.o.
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
package jetbrains.mps.ide.messages;

import com.intellij.icons.AllIcons;
import com.intellij.icons.AllIcons.General;
import com.intellij.ide.BrowserUtil;
import com.intellij.ide.CopyPasteManagerEx;
import com.intellij.ide.OccurenceNavigator;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.ui.InputValidatorEx;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.pom.NavigatableAdapter;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.components.JBList;
import com.intellij.usageView.UsageViewBundle;
import com.intellij.util.ExceptionUtil;
import com.intellij.util.ui.update.MergingUpdateQueue;
import com.intellij.util.ui.update.Update;
import jetbrains.mps.RuntimeFlags;
import jetbrains.mps.ide.actions.MPSActionPlaces;
import jetbrains.mps.ide.actions.MPSCommonDataKeys;
import jetbrains.mps.ide.search.SearchHistoryStorage;
import jetbrains.mps.messages.IMessage;
import jetbrains.mps.messages.IMessageHandler;
import jetbrains.mps.messages.IMessageList;
import jetbrains.mps.messages.MessageKind;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNodeId;
import org.jetbrains.mps.openapi.model.SNodeReference;

import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Distinction between MessageList and its sole subclass in MessagesViewTool is subtle and perhaps not worth
 * effort. Latter knows about IDEA's MessageView and Content, former is more about Swing UI and actions, both though depend
 * from IDEA platform. I doubt anyone would reuse this class any time soon.
 */
public abstract class MessageList implements IMessageList, SearchHistoryStorage, Disposable {

  private final MyToggleAction myWarningsAction = new MyToggleAction("Show Warning Messages", Icons.WARNING_ICON) {
    @Override
    protected boolean isEnabled() {
      return hasWarnings();
    }
  };
  private final MyToggleAction myInfoAction = new MyToggleAction("Show Informational Messages", Icons.INFORMATION_ICON) {
    @Override
    protected boolean isEnabled() {
      return hasInfo();
    }
  };
  private final MyToggleAction myAutoscrollToSourceAction = new MyToggleAction("Autoscroll to Source", Icons.AUTOSCROLLS_ICON) {
    @Override
    protected boolean isEnabled() {
      return hasHintObjects();
    }
  };

  private final Queue<IMessage> myMessages = new LinkedList<>();
  private MessagesListCellRenderer myCellRenderer = new MessagesListCellRenderer();
  protected int myInfos;
  protected int myWarnings;
  protected int myErrors;
  protected int myHintObjects;
  private final List<String> mySearches = new ArrayList<>();
  private int myMaxListSize = 10000;

  protected final FastListModel<IMessage> myModel = new FastListModel<>(this.myMaxListSize);
  private final RootPanel myComponent = new RootPanel();
  protected final JList myList = new JBList(myModel);
  private ActionToolbar myToolbar;
  private final AtomicInteger myMessagesInProgress = new AtomicInteger();
  private MessageToolSearchPanel mySearchPanel = null;
  private final MergingUpdateQueue myUpdateQueue = new MergingUpdateQueue("MessageList", 500, false, myComponent, this, null, true);
  private final Object myUpdateIdentity = new Object();
  private final ConcurrentLinkedQueue<IMessage> myMessagesQueue = new ConcurrentLinkedQueue<>();
  private volatile boolean myIsDisposed = false;
  private boolean myActivateOnMessage = false;

  protected MessageList() {
    myUpdateQueue.setRestartTimerOnAdd(true);
    // Recreate render to update colors after scheme change
    EditorColorsManager.getInstance().addEditorColorsListener(scheme -> {
      myCellRenderer = new MessagesListCellRenderer();
      myList.setCellRenderer(myCellRenderer);
    }, this);
  }

  /**
   * Tells whether the list shall show up once message is added.
   * XXX Note, there's difference between "just show up" and "show up and get active", but I don't know what is it
   * (i.e. whether window.show() brings the window to front, or it's windows.activate() that does, see #show(boolean), below.
   * Perhaps, it's just about focus gained?)
   * <p>
   * By default, we don't show the list when message is added.
   *
   * @see #bringToFront()
   */
  public void setActivateOnMessage(boolean activateOnMessage) {
    myActivateOnMessage = activateOnMessage;
  }

  @Override
  public void dispose() {
    myUpdateQueue.dispose();
    myIsDisposed = true;
  }

  @Override
  public void clear() {
    if (RuntimeFlags.isTestMode()) {
      return;
    }

    // 1. clear of the list shall happen synchronously with addition of messages, hence use of the same queue/update mechanism and same priority
    //    as update on #add()
    // 2. updates are not dispatched until view has been initialized (prevents exceptions like https://youtrack.jetbrains.com/issue/MPS-24408)
    // 3. though only one clear() in the queue makes sense, and we can ignore all but the last one, I didn't find a way to accomplish this with
    //    MergingUpdateQueue API. It seems possible to merge subsequent clear with the one already in the queue, but that would yield incorrect
    //    results (e.g. update requests: m1, m2, clear1, m3, clear2. If clear2 is ignored and merged with clear1, m3 would be visible)
    //    That's why identity is a new object each time (I don't expect thousands of successive clear() calls)
    myUpdateQueue.queue(new Update(new Object()) {
      @Override
      public void run() {
        if (myIsDisposed) {
          return;
        }
        myModel.clear();
        myMessages.clear();
        myErrors = 0;
        myWarnings = 0;
        myInfos = 0;
        myHintObjects = 0;
        myList.setFixedCellWidth(myList.getWidth());
        updateHeader();
        updateActions();
      }
    });
  }

  @Override
  public void add(@NotNull IMessage message) {
    if (RuntimeFlags.isTestMode()) {
      return;
    }

    myMessagesInProgress.incrementAndGet();
    myMessagesQueue.add(message);

    myUpdateQueue.queue(new Update(myUpdateIdentity) {
      @Override
      public void run() {
        if (myIsDisposed) {
          return;
        }

        List<IMessage> messagesToAdd = new ArrayList<>();
        while (!myMessagesQueue.isEmpty()) {
          IMessage message = myMessagesQueue.remove();
          myMessagesInProgress.decrementAndGet();

          if (isVisible(message)) {
            messagesToAdd.add(message);
          }
          myMessages.add(message);
          updateMessageCounters(message, 1);
        }

        int messagesToRemove = 0;
        if (myMessages.size() > MessageList.this.myMaxListSize) {
          for (int i = Math.min(myMessages.size() - MessageList.this.myMaxListSize, myMessages.size()); i > 0; i--) {
            IMessage toRemove = myMessages.remove();
            updateMessageCounters(toRemove, -1);
            if (isVisible(toRemove)) {
              messagesToRemove++;
            }
          }
          if (messagesToRemove > myModel.getSize()) {
            messagesToAdd = messagesToAdd.subList(messagesToRemove - myModel.getSize(), messagesToAdd.size());
            messagesToRemove = myModel.getSize();
          }
        }

        if (messagesToRemove > 0) {
          myModel.removeFirst(messagesToRemove);
        }
        myModel.addAll(messagesToAdd);

        int maxWidth = -1;
        for (IMessage message : messagesToAdd) {
          maxWidth = Math.max(maxWidth, getMessageWidth(message));
        }

        int index = myModel.getSize() - 1;
        if (myList.getAutoscrolls()) {
          myList.getSelectionModel().setSelectionInterval(index, index);
        }
        if (myMessagesInProgress.get() == 0) {
          myList.ensureIndexIsVisible(index);
        }

        if (maxWidth > myList.getFixedCellWidth()) {
          myList.setFixedCellWidth(maxWidth);
        }

        updateHeader();
        updateActions();
        if (myActivateOnMessage && messagesToAdd.size() > 0) {
          bringToFront();
        }
      }

      private void updateMessageCounters(IMessage m, int delta) {
        if (m.getKind() == MessageKind.ERROR) {
          myErrors += delta;
        }
        if (m.getKind() == MessageKind.WARNING) {
          myWarnings += delta;
        }
        if (m.getKind() == MessageKind.INFORMATION) {
          myInfos += delta;
        }
        if (m.getHintObject() != null) {
          myHintObjects += delta;
        }
      }
    });
  }

  @Override
  public void wake() {
    // for reasons why I use new identity for each call see #clear().
    // I don't expect a lot of wake calls, and see no reason to merge these calls (unless can ensure the last one added is respected)
    myUpdateQueue.queue(new Update(new Object()) {
      @Override
      public void run() {
        if (myIsDisposed) {
          return;
        }
        // perhaps, could record a state with number of messages and bring tool window to front only if the number has changed since last 'toFront' call?
        bringToFront();
      }
    });
  }

  @Override
  public List<String> getSearches() {
    return new ArrayList<>(mySearches);
  }

  @Override
  public void setSearches(List<String> searches) {
    mySearches.clear();
    mySearches.addAll(searches);
  }

  public JComponent getComponent() {
    return myComponent;
  }

  protected void initUI() {
    myList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    myList.setCellRenderer(myCellRenderer);

    DefaultActionGroup group = new DefaultActionGroup();
    group.add(myWarningsAction);
    group.add(myInfoAction);
    group.add(myAutoscrollToSourceAction);
    group.add(new MessagesLimitAction());
    group.add(new ClearAction());

    myToolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, group, false);

    myComponent.setToolbar(myToolbar.getComponent());
    final JScrollPane scrollPane = ScrollPaneFactory.createScrollPane(myList, true);
    // Add MouseWheelListener to scrollPane instead of myList itself, because otherwise scrollPane default behaviour will be blocked
    scrollPane.addMouseWheelListener(e -> {
      // Need to convert event to get right mouse point relative to list
      final MouseEvent mouseEvent = SwingUtilities.convertMouseEvent(e.getComponent(), e, myList);
      myCellRenderer.setIndexUnderMouse(myList.locationToIndex(mouseEvent.getPoint()));
    });
    myComponent.setContent(scrollPane);

    myComponent.registerKeyboardAction(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (mySearchPanel == null) {
          mySearchPanel = new MessageToolSearchPanel(myList, MessageList.this);
          myComponent.add(mySearchPanel, BorderLayout.NORTH);
        }
        mySearchPanel.activate();
      }
    }, KeyStroke.getKeyStroke('F', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    myList.setFixedCellHeight(myList.getFontMetrics(myList.getFont()).getHeight() + 5);

    final AbstractAction openCurrentMessage = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        openCurrentMessageIfPossible();
      }
    };
    myList.registerKeyboardAction(openCurrentMessage, KeyStroke.getKeyStroke("F4"), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    myList.registerKeyboardAction(openCurrentMessage, KeyStroke.getKeyStroke("ENTER"), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    myList.registerKeyboardAction(new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showHelpForCurrentMessage();
      }
    }, KeyStroke.getKeyStroke("F1"), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    myList.registerKeyboardAction(e -> selectAll(),
                                  KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()),
                                  JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    myList.addMouseListener(new MouseAdapter() {
      // Holds index of item, that was under cursor on mouse press action
      private int mousePressedIndex = -1;

      @Override
      public void mouseClicked(MouseEvent e) {
        boolean oneClickOpen = e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1 && myAutoscrollToSourceAction.isSelected(null);
        boolean twoClickOpen = e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1;
        if (oneClickOpen || twoClickOpen) {
          openCurrentMessageIfPossible();
        }
      }

      @Override
      public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
          showPopupMenu(e);
        } else {
          mousePressedIndex = myList.locationToIndex(e.getPoint());
        }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
          showPopupMenu(e);
          return;
        }
        int index = myList.locationToIndex(e.getPoint());
        if (mousePressedIndex != -1 && index != -1 && mousePressedIndex != index) {
          myList.addSelectionInterval(mousePressedIndex, index);
        }
        // reset saved value to be set on next mouse press
        mousePressedIndex = -1;
      }
    });

    // This does not select message after scroll, but adding mouse scroll listener will block scrolling
    myList.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseMoved(MouseEvent event) {
        int index = myList.locationToIndex(event.getPoint());

        final IMessage message = index != -1 && index >= myList.getFirstVisibleIndex() && index <= myList.getLastVisibleIndex()
                                 ? myModel.getElementAt(index) : null;
        if (message == null || !myAutoscrollToSourceAction.isSelected(null)) {
          myList.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
          myCellRenderer.setIndexUnderMouse(-1);
          myList.repaint();
          return;
        }

        boolean canNavigate = canNavigate(message);
        myList.setCursor(Cursor.getPredefinedCursor(canNavigate ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
        myCellRenderer.setIndexUnderMouse(index);
        myList.repaint();
      }
    });
  }

  protected void openCurrentMessageIfPossible() {
    final IMessage selectedMessage = (IMessage) myList.getSelectedValue();
    if (selectedMessage != null && canNavigate(selectedMessage)) {
      navigate(selectedMessage, true);
    }
  }

  private void selectAll() {
    myList.setSelectionInterval(0, myList.getModel().getSize() - 1);
  }

  /**
   * Need to be invoked in EDT.
   * Check if {@link IMessage} holds element, which can be navigated too
   *
   * @param message to be check for holding navigatable element
   * @return <code>true<code/> if message can provide navigatable element
   */
  protected abstract boolean canNavigate(@NotNull IMessage message);

  /**
   * Need to be invoked in EDT.
   * Check if {@link IMessage} holds element, which can be navigated too
   *
   * @param message holds navigatable element
   * @param focus   sets obligation to focus element
   */
  protected abstract void navigate(@NotNull IMessage message, boolean focus);

  /**
   * Invoke when visible messages are added, and list requires exposure to user
   */
  protected abstract void bringToFront();

  /**
   * Override and implement registration of UI component into the MessageView.
   * Don't forget to activate message dispatch once content is ready.
   *
   * @see #activateUpdate()
   */
  public abstract void createContent(final boolean canClose, final boolean isMultiple);

  /**
   * MessageList doesn't update once instantiated, activate update dispatch once
   * content is created ({@link #createContent(boolean, boolean)})
   */
  protected final void activateUpdate() {
    myUpdateQueue.activate();
  }

  public boolean isPinned() {
    return false;
  }

  private void showPopupMenu(MouseEvent event) {
    final int index = myList.locationToIndex(event.getPoint());
    final IMessage message = index != -1 ? myModel.getElementAt(index) : null;
    if (message != null && !myList.getSelectedValuesList().contains(message)) {
      myList.setSelectedIndex(index);
    }

    DefaultActionGroup group = createActionGroup();

    JPopupMenu menu = ActionManager.getInstance().createActionPopupMenu(MPSActionPlaces.MPS_MESSAGES_POPUP, group).getComponent();
    menu.show(myList, event.getX(), event.getY());
    event.consume();
  }

  @SuppressWarnings({"ThrowableInstanceNeverThrown", "unchecked"})
  private DefaultActionGroup createActionGroup() {
    DefaultActionGroup group = new DefaultActionGroup();

    final List<IMessage> selectedValues = myList.getSelectedValuesList();
    if (!selectedValues.isEmpty()) {
      final StringBuilder sb = new StringBuilder();
      for (IMessage message : selectedValues) {
        sb.append(message.getText());
        sb.append('\n');

        if (message.getException() != null) {
          sb.append(ExceptionUtil.getThrowableText(message.getException()));
        }
      }
      group.add(new CopyToClipboardAction("Copy Text").setTextToCopy(sb.toString()));
      Object hintObj;
      if (selectedValues.size() == 1 && (hintObj = (selectedValues.get(0)).getHintObject()) != null) {
        SNodeId nodeId = hintObj instanceof SNodeReference ? ((SNodeReference) hintObj).getNodeId() : null;
        if (nodeId != null) {
          group.add(new CopyToClipboardAction("Copy Node Id").setTextToCopy(nodeId.toString()));
        }
      }
    }

    group.addSeparator();

    group.add(new AnAction("Show Help for This Message", null, null) {
      @Override
      public void update(AnActionEvent e) {
        boolean enabled = getHelpUrlForCurrentMessage() != null;
        Presentation presentation = e.getPresentation();
        presentation.setEnabled(enabled);
        presentation.setVisible(enabled);
      }

      @Override
      public void actionPerformed(AnActionEvent e) {
        showHelpForCurrentMessage();
      }
    });

    group.addSeparator();
    populateActions(myList, group);
    group.addSeparator();

    group.add(new ClearAction());
    group.add(new SelectAllAction());

    return group;
  }

  protected abstract void populateActions(JList list, DefaultActionGroup group);

  private void showHelpForCurrentMessage() {
    String helpURL = getHelpUrlForCurrentMessage();
    if (helpURL == null) {
      return;
    }
    BrowserUtil.browse(helpURL);
  }

  private String getHelpUrlForCurrentMessage() {
    if (myList.getSelectedValuesList().size() != 1) {
      return null;
    }

    IMessage message = (IMessage) (myList.getSelectedValue());
    return message.getHelpUrl();
  }

  private void rebuildModel() {
    myModel.clear();
    myList.setFixedCellWidth(myList.getWidth());
    List<IMessage> messagesToAdd = new ArrayList<>();
    int width = 0;
    for (IMessage m : myMessages) {
      if (isVisible(m)) {
        width = Math.max(width, getMessageWidth(m));
        messagesToAdd.add(m);
      }
    }
    myList.setFixedCellWidth(width);

    myModel.addAll(messagesToAdd);
  }

  private int getMessageWidth(IMessage message) {
    Component renderer = myCellRenderer.getListCellRendererComponent(myList, message, 0, false, false);
    return renderer.getPreferredSize().width;
  }

  public boolean isVisible(IMessage m) {
    switch (m.getKind()) {
      case ERROR:
        return true;
      case WARNING:
        return myWarningsAction.isSelected(null);
      case INFORMATION:
        return myInfoAction.isSelected(null);
    }
    return true;
  }

  protected void updateHeader() {
  }

  private void updateActions() {
    if (myToolbar == null) {
      return;
    }
    myToolbar.updateActionsImmediately();
  }

  protected boolean hasErrors() {
    return myErrors > 0;
  }

  protected boolean hasWarnings() {
    return myWarnings > 0;
  }

  protected boolean hasInfo() {
    return myInfos > 0;
  }

  protected boolean hasHintObjects() {
    return myHintObjects > 0;
  }

  private static class CopyToClipboardAction extends AnAction {
    private String myTextToCopy;

    CopyToClipboardAction(String actionTitle) {
      super(actionTitle);
    }

    public CopyToClipboardAction setTextToCopy(String textToCopy) {
      myTextToCopy = textToCopy;
      return this;
    }

    @Override
    public void update(AnActionEvent e) {
      super.update(e);
      e.getPresentation().setEnabled(myTextToCopy != null);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
      CopyPasteManagerEx.getInstance().setContents(new StringSelection(myTextToCopy));
    }
  }

  private class MyToggleAction extends ToggleAction {
    private boolean mySelected;
    private final Icon myIcon;

    public MyToggleAction(String tooltip, Icon icon) {
      super("", tooltip, icon);
      myIcon = icon;
      mySelected = true;
    }

    @Override
    public boolean isSelected(AnActionEvent e) {
      return mySelected;
    }

    @Override
    public void setSelected(AnActionEvent e, boolean state) {
      mySelected = state;
      rebuildModel();
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
      super.update(e);

      boolean enabled = isEnabled();
      Icon icon = enabled ? myIcon : UIManager.getLookAndFeel().getDisabledIcon(null, myIcon);
      if (icon == null) {
        icon = myIcon;
      }
      e.getPresentation().setIcon(icon);
    }

    protected boolean isEnabled() {
      return true;
    }
  }

  private class MessagesLimitAction extends AnAction {
    private MessagesLimitAction() {
      super(General.Settings);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
      String result = Messages.showInputDialog(MessageList.this.myComponent, "Set max number of showing messages", "Messages Limit", null,
                                               String.valueOf(MessageList.this.myMaxListSize),
                                               new InputValidatorEx() {
                                                 @Nullable
                                                 @Override
                                                 public String getErrorText(String inputString) {
                                                   return checkInput(inputString) ? null : "Enter correct number";
                                                 }

                                                 @Override
                                                 public boolean checkInput(String inputString) {
                                                   try {
                                                     final Integer i = Integer.valueOf(inputString);
                                                     if (i < 1) {
                                                       return false;
                                                     }
                                                   } catch (NumberFormatException nfe) {
                                                     return false;
                                                   }
                                                   return true;
                                                 }

                                                 @Override
                                                 public boolean canClose(String inputString) {
                                                   return checkInput(inputString);
                                                 }
                                               });
      if (result != null) {
        MessageList.this.myMaxListSize = Integer.valueOf(result);
      }
    }
  }

  /*package*/ MessageListState getState() {
    return new MessageListState(myWarningsAction.isSelected(null), myInfoAction.isSelected(null), myAutoscrollToSourceAction.isSelected(null), mySearches,
                                myMaxListSize);
  }

  /*package*/ void loadState(MessageListState state) {
    myWarningsAction.setSelected(null, state.isWarnings());
    myInfoAction.setSelected(null, state.isInfo());
    myAutoscrollToSourceAction.setSelected(null, state.isAutoscrollToSource());
    setSearches(state.getSearches());
    myMaxListSize = state.getMaxListSize();
  }

  @Override
  public IMessageHandler restrict(@NotNull MessageKind atLeastOfKind) {
    setWarningsEnabled(MessageKind.WARNING.isSameOrGreaterSeverityThan(atLeastOfKind));
    setInfoEnabled(MessageKind.INFORMATION.isSameOrGreaterSeverityThan(atLeastOfKind));
    return this;
  }

  public void setWarningsEnabled(boolean enabled) {
    myWarningsAction.setSelected(null, enabled);
  }

  public void setInfoEnabled(boolean enabled) {
    myInfoAction.setSelected(null, enabled);
  }

  public /*for tests*/ static class FastListModel<T> extends AbstractListModel<T> {
    private int myStart;
    private int myEnd;
    private int mySize;
    private final T[] myItems;

    @SuppressWarnings("unchecked")
    FastListModel(int size) {
      myItems = (T[]) new Object[size];
      myStart = 0;
      myEnd = 0;
    }

    @Override
    public int getSize() {
      return mySize;
    }

    @Override
    public T getElementAt(int index) {
      return myItems[(myStart + index) % myItems.length];
    }

    public void add(T item) {
      if (mySize == myItems.length) {
        throw new RuntimeException("Buffer overflow");
      }
      myItems[myEnd] = item;
      myEnd = (myEnd + 1) % myItems.length;
      mySize++;
      fireIntervalAdded(this, mySize - 1, mySize - 1);
    }

    public void addAll(Collection<T> items) {
      if (items.isEmpty()) {
        return;
      }
      if (mySize + items.size() > myItems.length) {
        throw new RuntimeException("Buffer overflow");
      }
      int intervalStart = mySize;
      for (T item : items) {
        myItems[myEnd] = item;
        myEnd = (myEnd + 1) % myItems.length;
        mySize++;
      }
      fireIntervalAdded(this, intervalStart, mySize - 1);
    }

    public void removeFirst() {
      if (mySize == 0) {
        throw new RuntimeException("Buffer underflow");
      }
      myItems[myStart] = null;
      myStart = (myStart + 1) % myItems.length;
      mySize--;
      fireIntervalRemoved(this, 0, 0);
    }

    public void removeFirst(int count) {
      if (count <= 0) {
        throw new IllegalArgumentException("Illegal count value " + count);
      }
      if (mySize - count < 0) {
        throw new RuntimeException("Buffer underflow");
      }
      for (int i = 0; i < count; i++) {
        myItems[myStart] = null;
        myStart = (myStart + 1) % myItems.length;
        mySize--;
      }
      fireIntervalRemoved(this, 0, count - 1);
    }

    public void clear() {
      myStart = 0;
      myEnd = 0;
      int oldSize = mySize;
      mySize = 0;
      Arrays.fill(myItems, null);
      if (oldSize > 0) {
        fireIntervalRemoved(this, 0, oldSize - 1);
      }
    }
  }

  public static class MessageListState {
    private boolean myWarnings;
    private boolean myInfo;
    private boolean myAutoscrollToSource;
    private List<String> mySearches = new ArrayList<>();
    private int myMaxListSize = 10000;

    public MessageListState() {
    }

    public MessageListState(boolean warnings, boolean info, boolean autoscrollToSource, List<String> searches, int maxListSize) {
      myWarnings = warnings;
      myInfo = info;
      myAutoscrollToSource = autoscrollToSource;
      mySearches = new ArrayList<>(searches);
      myMaxListSize = maxListSize;
    }

    public boolean isWarnings() {
      return myWarnings;
    }

    public void setWarnings(boolean warnings) {
      myWarnings = warnings;
    }

    public boolean isInfo() {
      return myInfo;
    }

    public void setInfo(boolean info) {
      myInfo = info;
    }

    public boolean isAutoscrollToSource() {
      return myAutoscrollToSource;
    }

    public void setAutoscrollToSource(boolean autoscrollToSource) {
      myAutoscrollToSource = autoscrollToSource;
    }

    public List<String> getSearches() {
      return mySearches;
    }

    public void setSearches(List<String> searches) {
      mySearches = searches;
    }

    public int getMaxListSize() {
      return myMaxListSize;
    }

    public void setMaxListSize(int maxListSize) {
      myMaxListSize = maxListSize;
    }
  }

  private class RootPanel extends SimpleToolWindowPanel implements OccurenceNavigator {
    public RootPanel() {
      super(false, true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object getData(@NonNls String id) {
      if (MPSCommonDataKeys.EXCEPTION.getName().equals(id)) {
        Throwable exc = null;
        for (IMessage message : (List<IMessage>) myList.getSelectedValuesList()) {
          exc = message.getException();
          if (exc != null) {
            break;
          }
        }
        return exc;
      }
      if (MPSCommonDataKeys.MESSAGES.getName().equals(id)) {
        List selectedValues = myList.getSelectedValuesList();
        return selectedValues == null || selectedValues.isEmpty() ? null : selectedValues;
      }

      if (PlatformDataKeys.HELP_ID.is(id)) {
        return "ideaInterface.messageList";
      }

      return super.getData(id);

    }

    @Override
    public boolean hasNextOccurence() {
      return next(1, false) != null;
    }

    @Override
    public boolean hasPreviousOccurence() {
      return next(-1, false) != null;
    }

    @Override
    public OccurenceInfo goNextOccurence() {
      return next(1, true);
    }

    @Override
    public OccurenceInfo goPreviousOccurence() {
      return next(-1, true);
    }


    @Nullable
    private OccurenceInfo next(final int delta, boolean doMove) {
      int current = myList.getSelectedIndex();
      for (current += delta; current >= 0 && current < myModel.getSize(); current += delta) {
        IMessage msg = myModel.getElementAt(current);
        if (msg.getHintObject() == null) {
          continue;
        }
        if (doMove) {
          myList.setSelectedIndex(current);
          myList.ensureIndexIsVisible(current);
        }
        return new OccurenceInfo(new NavigatableAdapter() {
          @Override
          public void navigate(boolean requestFocus) {
            openCurrentMessageIfPossible();
          }
        }, current, myModel.getSize());
      }
      return null;
    }

    @Override
    public String getNextOccurenceActionName() {
      return UsageViewBundle.message("action.next.occurrence");
    }

    @Override
    public String getPreviousOccurenceActionName() {
      return UsageViewBundle.message("action.previous.occurrence");
    }
  }

  private class ClearAction extends AnAction {
    public ClearAction() {
      super("Clear", "Clear all messages", Icons.CLEAR_ICON);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
      clear();
    }
  }

  private class SelectAllAction extends AnAction {

    public SelectAllAction() {
      super("Select All", "", AllIcons.Actions.Selectall);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
      selectAll();
    }
  }
}
