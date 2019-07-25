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
package jetbrains.mps.ide.tools;

import com.intellij.ide.actions.ActivateToolWindowAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.KeyboardShortcut;
import com.intellij.openapi.keymap.Keymap;
import com.intellij.openapi.keymap.KeymapManager;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import com.intellij.util.ui.update.UiNotifyConnector;
import jetbrains.mps.ide.ThreadUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class BaseTool {
  private final static Logger LOG = LogManager.getLogger(BaseTool.class);

  private final Project myProject;
  private final String myId;
  private Icon myIcon;
  private Map<String, KeyStroke> myShortcutsByKeymap;
  private final ToolWindowAnchor myAnchor;
  private final boolean mySideTool;
  private boolean myCanCloseContent;
  private boolean myIsRegistered;
  private ToolWindowManager myWindowManager;

  private JComponent myComponent = null;

  protected static Map<String, KeyStroke> shortcutsFromNumber(int number) {
    if (number == -1) {
      return Collections.emptyMap();
    }
    Map<String, KeyStroke> result = new HashMap<>(6); // avoiding rehash & decrease default capacity
    result.put(KeymapManager.DEFAULT_IDEA_KEYMAP, KeyStroke.getKeyStroke("alt " + number));
    result.put(KeymapManager.MAC_OS_X_KEYMAP, KeyStroke.getKeyStroke("meta " + number));
    result.put(KeymapManager.MAC_OS_X_10_5_PLUS_KEYMAP, KeyStroke.getKeyStroke("meta " + number));
    return result;
  }

  public BaseTool(Project project, String id, Map<String, KeyStroke> shortcutsByKeymap, Icon icon, ToolWindowAnchor anchor, boolean sideTool,
      boolean canCloseContent) {
    myAnchor = anchor;
    mySideTool = sideTool;
    myShortcutsByKeymap = shortcutsByKeymap;
    myId = id;
    myIcon = icon;
    myCanCloseContent = canCloseContent;
    myProject = project;
    myIsRegistered = false;
  }

  public String getId() {
    return myId;
  }

  public Icon getIcon() {
    return myIcon;
  }

  private boolean isRegistered() {
    return myIsRegistered;
  }

  public boolean toolIsOpened() {
    ThreadUtils.assertEDT();
    return getToolWindow().isVisible();
  }

  /**
   * Runs {@link jetbrains.mps.ide.tools.BaseTool#openTool} later in EDT event pool.
   *
   * @param setActive determine if tool window must be just opened or additionally became active and attract focus
   */
  public void openToolLater(final boolean setActive) {
    ThreadUtils.runInUIThreadNoWait(() -> openTool(setActive));
  }

  /**
   * Opens the tool's window, shows tool if invisible at the moment.
   * Need to be called in EDT.
   *
   * @param setActive determine if tool window must be just opened or additionally became active and attract focus
   */
  public void openTool(boolean setActive) {
    ThreadUtils.assertEDT();
    ToolWindow window = getToolWindow();
    if (!isAvailable()) {
      makeAvailable();
    }
    if (!toolIsOpened()) {
      window.show(null);
    }
    if (setActive) {
      window.activate(null);
    }
  }

  /**
   * Minimizes the window, doesn't remove tool from panel
   */
  public void close() {
    ThreadUtils.assertEDT();
    if (isAvailable() && toolIsOpened()) {
      getToolWindow().hide(null);
    }
  }

  /**
   * @return whether the tool is visible by user (in the panel)
   */
  public boolean isAvailable() {
    ThreadUtils.assertEDT();
    return getToolWindow().isAvailable();
  }

  public void setAvailable(boolean state) {
    ThreadUtils.assertEDT();
    if (state) {
      makeAvailable();
    } else {
      makeUnavailable();
    }
  }

  /**
   * Runs {@link jetbrains.mps.ide.tools.BaseTool#makeAvailable} later in EDT event pool.
   */
  public void makeAvailableLater() {
    ThreadUtils.runInUIThreadNoWait(this::makeAvailable);
  }

  /**
   * If the tool is visible, does nothing, else show the tool in panel in minimized state
   */
  public void makeAvailable() {
    ThreadUtils.assertEDT();
    if (!isAvailable()) {
      getToolWindow().setAvailable(true, null);
    }
  }

  /**
   * Runs {@link jetbrains.mps.ide.tools.BaseTool#makeUnavailable} later in EDT event pool.
   */
  public void makeUnavailableLater() {
    ThreadUtils.runInUIThreadNoWait(this::makeUnavailable);
  }

  /**
   * Removes the tool from the panel
   */
  public void makeUnavailable() {
    ThreadUtils.assertEDT();
    if (isAvailable()) {
      getToolWindow().setAvailable(false, null);
    }
  }

  public ToolWindow getToolWindow() {
    ThreadUtils.assertEDT();

    if (!isRegistered()) {
      register();
    }
    // register() may fail if myProject hasn't been initialized - ToolWindowManager is a ProjectComponent
    return myWindowManager == null ? null : myWindowManager.getToolWindow(myId);
  }

  @NonNls
  @NotNull
  public String getComponentName() {
    return getClass().getName();
  }

  public void registerLater() {
    ThreadUtils.runInUIThreadNoWait(() -> DumbService.getInstance(getProject()).runWhenSmart(this::register));
  }

  public final void register() {
    if (myProject.isDisposed()) {
      return;
    }
    if (isRegistered()) {
      return;
    }
    ThreadUtils.assertEDT();
    myIsRegistered = true;

    myWindowManager = ToolWindowManager.getInstance(myProject);

    if (myShortcutsByKeymap != null) {
      String actionId = ActivateToolWindowAction.getActionIdForToolWindow(myId);

      List<Keymap> keymaps = new ArrayList<>(myShortcutsByKeymap.size());
      for (Entry<String, KeyStroke> keymapItem : myShortcutsByKeymap.entrySet()) {
        Keymap keymap = KeymapManager.getInstance().getKeymap(keymapItem.getKey());
        if (keymap == null) {
          LOG.warn("Keymap " + keymapItem.getKey() + " cannot be found");
          return;
        }
        keymaps.add(keymap);
      }
      // keymaps topsort here is needed because we need to remove inherited shortcuts if they are overwritten
      Collections.sort(keymaps, (o1, o2) -> {
        for (Keymap parent = o1.getParent(); parent != null; parent = parent.getParent()) {
          if (parent.equals(o2)) {
            return 1;
          }
        }
        for (Keymap parent = o2.getParent(); parent != null; parent = parent.getParent()) {
          if (parent.equals(o1)) {
            return -1;
          }
        }
        return 0;
      });

      for (Keymap keymap : keymaps) {
        KeyboardShortcut defShortcut = new KeyboardShortcut(myShortcutsByKeymap.get(keymap.getName()), null);
        keymap.removeAllActionShortcuts(actionId);
        keymap.addShortcut(actionId, defShortcut);
      }
    }

    //if we create a new project, tool windows are created for it automatically
    ToolWindow toolWindow = myWindowManager.getToolWindow(myId);
    if (toolWindow == null) {
      toolWindow = myWindowManager.registerToolWindow(myId, myCanCloseContent, myAnchor, getProject(), true, mySideTool);
    }
    if (myIcon != null) {
      toolWindow.setIcon(myIcon);
    }

    toolWindow.setToHideOnEmptyContent(true);
    toolWindow.installWatcher(toolWindow.getContentManager());
    setAvailable(isInitiallyAvailable());

    doRegister();

    UiNotifyConnector.doWhenFirstShown(toolWindow.getComponent(), () -> {
      if (myComponent == null) {
        myComponent = getComponent();
      }
      if (myComponent != null) {
        addContent(myComponent, "", null, false);
      }
    });
  }

  /**
   * Override this method to add implementation specific registration.
   * Called in {@link jetbrains.mps.ide.tools.BaseTool#register} before {@link jetbrains.mps.ide.tools.BaseTool#getComponent()}.
   */
  protected void doRegister() {

  }

  public int getCurrentTabIndex() {
    final ContentManager contentManager = getContentManager();
    final Content selectedContent = contentManager.getSelectedContent();
    return selectedContent == null ? -1 : contentManager.getIndexOfContent(selectedContent);
  }

  protected AnAction createCloseAction() {
    return new CloseAction(this);
  }

  protected boolean isInitiallyAvailable() {
    return false;
  }


  /**
   * Runs {@link jetbrains.mps.ide.tools.BaseTool#unregister instead} later in EDT event pool.
   */
  // TODO: remove unused?
  public void unregisterLater() {
    ThreadUtils.runInUIThreadNoWait(this::unregister);
  }

  /**
   * Unregister Tool and removes all shortcuts in case of reload.
   * Need to be called in EDT.
   * <p>
   * If project is closing (== not in opened projects) {
   * <p>
   * }, but there are some other opened projects,
   * than shortcuts must not be removed - instance of BaseTool still exists for other projects
   * and shortcuts are global (registered by ActionId).
   * In case of BaseTool reload (unregister on opened project) we need do this,
   * because it will register (probably changed) shortcuts back on load.
   */
  public final void unregister() {
    if (!isRegistered()) {
      return;
    }
    ThreadUtils.assertEDT();

    doUnregister();

    // see Javadoc for if condition explanation
    final List<Project> openedProjects = Arrays.asList(ProjectManager.getInstance().getOpenProjects());
    if (myShortcutsByKeymap != null && (openedProjects.contains(getProject()) || openedProjects.isEmpty())) {
      for (Entry<String, KeyStroke> keymapItem : myShortcutsByKeymap.entrySet()) {
        Keymap keymap = KeymapManager.getInstance().getKeymap(keymapItem.getKey());
        if (keymap != null) {
          keymap.removeAllActionShortcuts(ActivateToolWindowAction.getActionIdForToolWindow(myId));
        }
      }
    }

    ToolWindow toolWindow = getToolWindow();
    if (toolWindow != null) {
      ContentManager contentManager = toolWindow.getContentManager();
      if (contentManager != null && !contentManager.isDisposed()) {
        contentManager.removeAllContents(true);
      }
    }

    myWindowManager.unregisterToolWindow(myId);
    myIsRegistered = false;
  }

  /**
   * Override this method to add implementation specific unregistration.
   * Called in {@link jetbrains.mps.ide.tools.BaseTool#unregister} before default unregister process.
   */
  protected void doUnregister() {

  }

  // TODO: make method abstract - fix jetbrains.mps.ide.findusages.view.UsagesViewTool
  public JComponent getComponent() {
    return null;
  }

  protected Content addContent(JComponent component, @NotNull String name, Icon icon, boolean isLockable) {
    ContentManager contentManager = getContentManager();
    Content content = contentManager.getFactory().createContent(component, name, isLockable);
    if (icon != null) {
      content.putUserData(ToolWindow.SHOW_CONTENT_ICON, Boolean.TRUE);
      content.setIcon(icon);
    } else {
      content.setIcon(myIcon);
    }
    contentManager.addContent(content);
    return content;
  }

  public void setSelectedComponent(JComponent component) {
    ContentManager manager = getContentManager();
    Content content = manager.getContent(component);
    manager.setSelectedContent(content);
  }

  protected ContentManager getContentManager() {
    if (!isRegistered()) {
      register();
    }
    if (getToolWindow() == null) {
      return null;
    }
    return getToolWindow().getContentManager();
  }

  @Override
  public String toString() {
    return "Tool " + this.getComponentName();
  }

  protected Project getProject() {
    return myProject;
  }

  public void init(Project project) {
  }

  public void dispose() {

  }
}
