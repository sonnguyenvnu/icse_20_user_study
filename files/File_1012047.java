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

import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowId;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import com.intellij.ui.content.MessageView;
import com.intellij.ui.content.MessageView.SERVICE;
import jetbrains.mps.RuntimeFlags;
import jetbrains.mps.ide.ThreadUtils.RunInUIRunnable;
import jetbrains.mps.ide.messages.MessageList.MessageListState;
import jetbrains.mps.ide.messages.MessagesViewTool.MessageViewToolState;
import jetbrains.mps.ide.messages.navigation.NavigationManager;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.messages.IMessage;
import jetbrains.mps.messages.IMessageHandler;
import jetbrains.mps.messages.IMessageList;
import jetbrains.mps.messages.Message;
import jetbrains.mps.messages.MessageKind;
import org.jetbrains.annotations.NotNull;

import javax.swing.JList;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@State(
    name = "MessagesViewTool",
    storages = @Storage(StoragePathMacros.WORKSPACE_FILE)
)
public class MessagesViewTool implements ProjectComponent, PersistentStateComponent<MessageViewToolState>, Disposable {
  private static final String DEFAULT_LIST = "DEFAULT_LIST";

  private final Project myProject;
  private final NavigationManager myNavigationManager;
  private final ToolWindowManager myToolWindowManager;
  private final MyMessageList myDefaultList;
  private final Map<Object, List<MessageList>> myMessageLists = new HashMap<>();
  private final MessageViewLoggingHandler myMessageViewLoggingHandler;

  public MessagesViewTool(Project project, NavigationManager navigationManager, ToolWindowManager toolWindowManager) {
    myProject = project;
    myNavigationManager = navigationManager;
    myToolWindowManager = toolWindowManager;
    myDefaultList = new MyMessageList("Messages");
    Disposer.register(this, myDefaultList);
    // default list doesn't need too much attention, don't activate it on any message
    myDefaultList.setActivateOnMessage(false);
    myDefaultList.setTitleUpdateFormat(
        "{1,choice,0#--|1#1 error|2#{1} errors}/{2,choice,0#--|1#1 warning|2#{2} warnings}/{3,choice,0#--|1#1 info|2#{3} infos}");
    addList(DEFAULT_LIST, myDefaultList);
    myMessageViewLoggingHandler = new MessageViewLoggingHandler(this, ProjectHelper.fromIdeaProject(project));
  }

  @Override
  public void dispose() {
    myMessageLists.clear();
  }

  public Project getProject() {
    return myProject;
  }

  public void clear() {
    getDefaultList().clear();
  }

  public void clear(String listName) {
    MessageList list = getAvailableList(listName, false);
    if (list != null) {
      list.clear();
    }
  }

  public void add(final IMessage message) {
    getDefaultList().add(message);
  }

  public void add(final IMessage message, String listName) {
    getAvailableList(listName, true).add(message);
  }

  @Override
  @NotNull
  public String getComponentName() {
    return MessagesViewTool.class.getSimpleName();
  }

  @Override
  public void initComponent() {
    getDefaultList().createContent(false, false);
  }

  @Override
  public void disposeComponent() {
    Disposer.dispose(this);
  }

  @Override
  public void projectOpened() {
    myMessageViewLoggingHandler.register();
  }

  @Override
  public void projectClosed() {
    myMessageViewLoggingHandler.unregister();
  }

  public static class MessageViewToolState {
    public MessageViewToolState(MessageListState defaultListState) {
      this.defaultListState = defaultListState;
    }

    public MessageViewToolState() {
      // default cons is essential for IDEA to construct the state.
      this.defaultListState = new MessageListState();
    }

    public MessageListState defaultListState;
  }

  @Override
  public MessageViewToolState getState() {
    return new MessageViewToolState(getDefaultList().getState());
  }

  @Override
  public void loadState(@NotNull MessageViewToolState state) {
    getDefaultList().loadState(state.defaultListState);
  }

  /*package*/ MessageView getMessagesService() {
    return SERVICE.getInstance(myProject);
  }

  public IMessageHandler newHandler() {
    return getDefaultList();
  }

  /**
   * Shorthand for {@link #newHandler(String, boolean) newHandler(name, false)}.
   */
  public IMessageHandler newHandler(@NotNull final String name) {
    return newHandler(name, false /*newHandler used to keep the list intact in MPS releases to date*/);
  }

  /**
   * @param name identifies named collection of messages, value is visible in UI as the name of the collection.
   * @param clear true if caller doesn't need to keep messages already reported under same named handler (if any)
   * @return handler that pipes messages to designated UI component, never {@code null}
   */
  public IMessageHandler newHandler(@NotNull final String name, boolean clear) {
    MessageList availableList = getAvailableList(name, true);
    if (clear) {
      availableList.clear();
    }
    return availableList;
  }

  /**
   * Creates/retrieves existing named collection of messages, with respect to supplied options.
   * @param name name of the list. It's up to caller to provide reasonable name in case of {@link MessageListOptions#AlwaysNew} to tell one list from another.
   * @param options if no options specified, {@link MessageListOptions#ReuseExisting} + {@link MessageListOptions#ActivateOnMessage} is assumed.
   * @return UI-backed collection of messages.
   */
  @NotNull
  public IMessageList getMessageList(@NotNull final String name, MessageListOptions... options) {
    List<MessageListOptions> optionsList = Arrays.asList(options);
    boolean alwaysNew = optionsList.contains(MessageListOptions.AlwaysNew);
    boolean reuseExisting = !alwaysNew && (optionsList.isEmpty() || optionsList.contains(MessageListOptions.ReuseExisting));
    boolean activateOnMessage = !optionsList.contains(MessageListOptions.DeafOnMessage) || optionsList.contains(MessageListOptions.ActivateOnMessage);
    MessageList list;
    if (alwaysNew) {
      list = createList(name);
    } else {
      list = getAvailableList(name, reuseExisting);
    }
    list.setActivateOnMessage(activateOnMessage);
    return list;
  }

  private synchronized void addList(String name, MessageList list) {
    List<MessageList> lists = myMessageLists.containsKey(name) ? myMessageLists.get(name) : new ArrayList<>();
    if (!myMessageLists.containsKey(name)) {
      myMessageLists.put(name, lists);
    }
    lists.add(list);
  }

  /**
   * @deprecated I don't feel it's a nice idea to expose implementation detail, {@link #newHandler()} shall suffice, perhaps,
   *             augmented with options object to pass info/warn/clear settings
   */
  @Deprecated
  public synchronized MessageList getAvailableList(String name, boolean createIfNotFound) {
    List<MessageList> lists;
    if (myMessageLists.containsKey(name)) {
      lists = myMessageLists.get(name);
    } else {
      myMessageLists.put(name, lists = new ArrayList<>());
    }
    for (int i = lists.size() - 1; i >= 0; --i) {
      MessageList messageList = lists.get(i);
      if (!messageList.isPinned()) {
        return messageList;
      }
    }
    if (createIfNotFound) {
      MessageList list = createList(name);
      lists.add(list);
      return list;
    }
    return null;
  }

  private synchronized MessageList createList(String name) {
    MyMessageList list = new MyMessageList(name);
    Disposer.register(this, list);
    list.loadState(getDefaultList().getState());
    list.setActivateOnMessage(true);
    list.createContent(true, true);
    return list;
  }

  /*package*/
  synchronized void removeList(MessageList list, String name) {
    List<MessageList> lists = myMessageLists.get(name);
    if (lists != null) {
      lists.remove(list);
    }
  }

  private MessageList getDefaultList() {
    return myDefaultList;
  }

  private class MyMessageList extends MessageList {
    private final String myTitle;
    private String myTitleUpdateFormat =
        "{0}: {1,choice,0#--|1#1 error|2#{1} errors}/{2,choice,0#--|1#1 warning|2#{2} warnings}/{3,choice,0#--|1#1 info|2#{3} infos}";
    /*
     * getMessagesService().getContentManager() may fail with NPE as respective tool window is initialized as post-startup activity
     * while users are quite quick to run rebuild (or another messages client). One way to prevent NPE is to check
     * {@code myToolWindowManager.getToolWindow(ToolWindowId.MESSAGES_WINDOW) != null}, another is to track whether our createContent() completed.
     */
    private boolean myContentReady = false;

    protected MyMessageList(@NotNull String title) {
      myTitle = title;
    }

    @Override
    public void dispose() {
      myContentReady = false;
      super.dispose();
      removeList(this, myTitle);
    }

    @Override
    protected void bringToFront() {
      ToolWindow window = myToolWindowManager.getToolWindow(ToolWindowId.MESSAGES_WINDOW);
      if (window == null) {
        return; // just in case
      }
      if (!window.isAvailable()) {
        window.setAvailable(true, null);
      }
      if (!window.isVisible()) {
        window.show(null);
      }
      Content content = getMessagesService().getContentManager().getContent(getComponent());
      getMessagesService().getContentManager().setSelectedContent(content);
    }

    public void setTitleUpdateFormat(String pattern) {
      myTitleUpdateFormat = pattern;
    }

    public void createContent(final boolean canClose, final boolean isMultiple) {
      if (RuntimeFlags.isTestMode()) {
        return;
      }

      final Runnable initRunnable = () -> {
        initUI();
        final MessageView service = getMessagesService();
        Content content = service.getContentManager().getFactory().createContent(getComponent(), myTitle, true);

        content.setCloseable(canClose);
        content.setPinnable(isMultiple);
        if (canClose) {
          content.setShouldDisposeContent(true);
          content.setDisposer(MyMessageList.this);
        }
        content.putUserData(ToolWindow.SHOW_CONTENT_ICON, Boolean.TRUE);
        service.getContentManager().addContent(content);
        activateUpdate();
        myContentReady = true;
      };
      getMessagesService().runWhenInitialized(new RunInUIRunnable(initRunnable, false));
    }

    @Override
    public boolean isPinned() {
      if (myContentReady) {
        ContentManager contentManager = getMessagesService().getContentManager();
        Content content = contentManager != null ? contentManager.getContent(getComponent()) : null;
        return content != null && content.isPinned();
      }
      return super.isPinned();
    }

    @Override
    protected void updateHeader() {
      if (myTitle.equals(myTitleUpdateFormat) || myTitleUpdateFormat == null) {
        return;
      }
      Content content = getMessagesService().getContentManager().getContent(getComponent());
      if (content != null) {
        if (hasErrors() || hasWarnings() || hasInfo()) {
          final String t = MessageFormat.format(myTitleUpdateFormat, myTitle, myErrors, myWarnings, myInfos);
          content.setDisplayName(t);
        } else {
          content.setDisplayName(myTitle);
        }
      }
    }

    @Override
    protected boolean canNavigate(@NotNull IMessage message) {
      return message.getHintObject() != null && myNavigationManager.canNavigateTo(message.getHintObject());
    }

    @Override
    protected void navigate(@NotNull IMessage message, boolean focus) {
      // XXX could receive Navigatable from NM and in case navigation fails (e.g. due to deleted/missing node)
      // could show a balloon with explanation (it's better to do it here rather than in Navigatable itself as here we've
      // got tool window to anchor.
      myNavigationManager.navigateTo(message.getHintObject(), focus);
    }

    @Override
    protected void populateActions(JList list, DefaultActionGroup group) {
      ActionGroup acts = (ActionGroup) ActionManager.getInstance().getAction("MPS.MessagesView");
      group.addAll(acts);
    }
  }

  public static void log(Project p, MessageKind kind, String message) {
    p.getComponent(MessagesViewTool.class).add(new Message(kind, message));
  }

}
