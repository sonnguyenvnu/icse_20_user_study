/*
 * Copyright 2003-2016 JetBrains s.r.o.
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

import com.intellij.util.containers.SortedList;
import jetbrains.mps.ide.ThreadUtils;
import jetbrains.mps.nodeEditor.inspector.InspectorEditorComponent;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.EditorCell_Collection;
import jetbrains.mps.openapi.editor.message.EditorMessageOwner;
import jetbrains.mps.openapi.editor.message.SimpleEditorMessage;
import jetbrains.mps.openapi.editor.update.UpdaterListener;
import jetbrains.mps.openapi.editor.update.UpdaterListenerAdapter;
import jetbrains.mps.util.containers.ManyToManyMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.module.ModelAccess;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class NodeHighlightManager implements EditorMessageOwner {
  private static final Comparator<SimpleEditorMessage> EDITOR_MESSAGES_COMPARATOR = (m1, m2) -> {
    if (m1.getPriority() != m2.getPriority()) {
      return m1.getPriority() - m2.getPriority();
    }
    return m1.getStatus().ordinal() - m2.getStatus().ordinal();
  };

  // TODO: replace myMessagesLock usages with this ?
  private final Object myMessagesLock = new Object();

  @NotNull
  private final EditorComponent myEditor;
  private final Set<SimpleEditorMessage> myMessages = new HashSet<>();
  private final Map<EditorMessageOwner, Set<SimpleEditorMessage>> myOwnerToMessages = new HashMap<>();
  private final ManyToManyMap<SimpleEditorMessage, SNode> myMessagesToNodes = new ManyToManyMap<>();

  /**
   * All caches are synchronized using myMessagesLock
   */
  private Map<EditorCell, List<SimpleEditorMessage>> myMessagesCache = Collections.emptyMap();
  private volatile boolean myRebuildMessagesCache = false;
  private final UpdaterListener myRebuildListener = new RebuildMessagesOnEditorUpdate();
  private Set<EditorMessageIconRenderer> myIconRenderersCache = new HashSet<>();
  private volatile boolean myRebuildIconRenderersCacheFlag = false;
  private boolean myDisposed = false;

  public NodeHighlightManager(@NotNull EditorComponent editor) {
    myEditor = editor;
    myEditor.getUpdater().addListener(myRebuildListener);
  }

  /**
   * scheduling lazy rebuild of myMessagesCache and myIconRenderersCache
   * this method can be called from any thread
   * this method should be called inside synchronize(myMessagesLock) block only
   */
  private void invalidateMessagesCaches() {
    myRebuildMessagesCache = true;
    myRebuildIconRenderersCacheFlag = true;
  }

  private Map<EditorCell, List<SimpleEditorMessage>> getMessagesCache() {
    synchronized (myMessagesLock) {
      return myMessagesCache;
    }
  }

  private void refreshMessagesCache() {
    assert ThreadUtils.isInEDT() : "refreshMessagesCache() should be called from EDT only";
    assert getModelAccess().canRead() : "refreshMessagesCache() should be called inside model read action only";
    synchronized (myMessagesLock) {
      if (!myRebuildMessagesCache) {
        return;
      }

      myRebuildMessagesCache = false;
      if (myMessages.isEmpty() || myEditor.getRootCell() == null) {
        myMessagesCache = Collections.emptyMap();
      } else {
        myMessagesCache = new HashMap<>();
        rebuildMessages(myEditor.getRootCell());
      }
    }
  }

  private ModelAccess getModelAccess() {
    return myEditor.getRepository().getModelAccess();
  }

  /**
   * part of myMessagesCache rebuild process
   * this method should be called inside synchronize(myMessagesLock) block only
   */
  private void rebuildMessages(EditorCell root) {
    List<SimpleEditorMessage> messages = calculateMessages(root);
    if (!messages.isEmpty()) {
      myMessagesCache.put(root, messages);
    }

    if (root instanceof EditorCell_Collection) {
      for (EditorCell cell : (EditorCell_Collection) root) {
        rebuildMessages(cell);
      }
    }
  }

  public List<SimpleEditorMessage> getMessages(EditorCell cell) {
    assert !myDisposed;
    List<SimpleEditorMessage> result = getMessagesCache().get(cell);
    if (result != null) {
      return new ArrayList<>(result);
    }
    return Collections.emptyList();
  }

  /**
   * part of myMessagesCache rebuild process
   * this method should be called inside synchronize(myMessagesLock) block only
   */
  private List<SimpleEditorMessage> calculateMessages(EditorCell cell) {
    final SNode node = cell.getSNode();
    if (node == null) {
      return Collections.emptyList();
    }

    final List<SimpleEditorMessage> result = new SortedList<>(EDITOR_MESSAGES_COMPARATOR);
    Set<SimpleEditorMessage> messageSet = myMessagesToNodes.getBySecond(node);
    for (SimpleEditorMessage message : messageSet) {
      if (!(message instanceof EditorMessage) || ((EditorMessage) message).acceptCell(cell, myEditor)) {
        result.add(message);
      }
    }
    if (myEditor.getRootCell() != cell || !(myEditor instanceof InspectorEditorComponent)) {
      // the condition above is because an inspector for the node
      // does not have cells for some node's children (they are edited in main editor)
      // but the cell should not be highlighted only because of this
      if (cell.isBig()) {
        for (SNode child : node.getChildren()) {
          EditorCell cellForChild = myEditor.findNodeCell(child);
          if (cellForChild == null) {
            getMessagesFromDescendants(child, result);
          }
        }
      }
    }
    return result;
  }

  private void getMessagesFromDescendants(SNode nodeWithoutCell, List<SimpleEditorMessage> messages) {
    messages.addAll(myMessagesToNodes.getBySecond(nodeWithoutCell));
    for (SNode child : nodeWithoutCell.getChildren()) {
      EditorCell cellForChild = myEditor.findNodeCell(child);
      if (cellForChild == null) {
        getMessagesFromDescendants(child, messages);
      }
    }
  }

  private void addMessage(SimpleEditorMessage m) {
    if (m.getNode() == null) {
      return;
    }

    EditorMessageOwner owner = m.getOwner();
    if (!myOwnerToMessages.containsKey(owner)) {
      myOwnerToMessages.put(owner, new HashSet<>());
    }
    myOwnerToMessages.get(owner).add(m);
    myMessages.add(m);

    myMessagesToNodes.addLink(m, m.getNode());
  }

  private boolean removeMessage(SimpleEditorMessage m) {
    if (m == null) {
      return false;
    }
    EditorMessageOwner owner = m.getOwner();
    Set<SimpleEditorMessage> messages = myOwnerToMessages.get(owner);
    if (messages != null) {
      messages.remove(m);
      if (messages.isEmpty()) {
        myOwnerToMessages.remove(owner);
      }
    }
    myMessages.remove(m);
    if (myEditor.hasUI()) {
      myEditor.getMessagesGutter().remove(m);
    }

    myMessagesToNodes.clearFirst(m);
    return true;
  }

  public void mark(SimpleEditorMessage message) {
    for (SimpleEditorMessage msg : getMessages()) {
      if (msg.sameAs(message)) return;
    }

    synchronized (myMessagesLock) {
      addMessage(message);
      invalidateMessagesCaches();
    }
    if (message.showInGutter() && myEditor.hasUI()) {
      myEditor.getMessagesGutter().add(message);
    }
  }

  public void unmark(SimpleEditorMessage message) {
    synchronized (myMessagesLock) {
      if (removeMessage(message)) {
        invalidateMessagesCaches();
      }
    }
  }

  public boolean clearForOwner(EditorMessageOwner owner) {
    return clearForOwner(owner, true);
  }

  public boolean hasMessages(EditorMessageOwner owner) {
    synchronized (myMessagesLock) {
      return myOwnerToMessages.containsKey(owner);
    }
  }

  public boolean clearForOwner(EditorMessageOwner owner, boolean repaintAndRebuild) {
    boolean result = myEditor.getMessagesGutter().removeMessages(owner);
    synchronized (myMessagesLock) {
      if (myOwnerToMessages.containsKey(owner)) {
        ArrayList<SimpleEditorMessage> messages = new ArrayList<>(myOwnerToMessages.get(owner));
        for (SimpleEditorMessage m : messages) {
          removeMessage(m);
        }
        invalidateMessagesCaches();
      }
    }
    if (repaintAndRebuild) {
      repaintAndRebuildEditorMessages();
    }
    return result;
  }

  /**
   * perform refresh of messages visible in LeftEditorHighlighter
   * and repaint associated EditorComponent
   */
  public void repaintAndRebuildEditorMessages() {
    getModelAccess().runReadInEDT(() -> {
      if (myDisposed) {
        return;
      }
      refreshMessagesCache();
      if (myEditor.hasUI()) {
        refreshLeftHighlighterMessages();
        myEditor.repaintExternalComponent();
      }
    });
  }

  private void refreshLeftHighlighterMessages() {
    assert ThreadUtils.isInEDT() : "refreshLeftHighlighterMessages() should be called from EDT only";
    Set<EditorMessageIconRenderer> oldIconRenderers;
    Set<EditorMessageIconRenderer> newIconRenderers;
    synchronized (myMessagesLock) {
      if (!myRebuildIconRenderersCacheFlag) {
        return;
      }
      myRebuildIconRenderersCacheFlag = false;
      oldIconRenderers = myIconRenderersCache;
      newIconRenderers = myIconRenderersCache = new HashSet<>();
      for (SimpleEditorMessage message : myMessages) {
        if (message instanceof EditorMessageIconRenderer) {
          myIconRenderersCache.add((EditorMessageIconRenderer) message);
        }
      }
    }
    myEditor.getLeftEditorHighlighter().removeAllIconRenderers(oldIconRenderers);
    myEditor.getLeftEditorHighlighter().addAllIconRenderers(newIconRenderers);
  }

  public void mark(SNode node, Color color, String messageText, EditorMessageOwner owner) {
    if (node == null) return;
    mark(new DefaultEditorMessage(node, color, messageText, owner));
  }

  public void mark(List<? extends SimpleEditorMessage> messages) {
    for (SimpleEditorMessage message : messages) {
      mark(message);
    }
    repaintAndRebuildEditorMessages();
  }

  /**
   * Should work even if NodeHighlightManager is disposed because it can be called by the Highlighter thread
   */
  public Set<SimpleEditorMessage> getMessages() {
    Set<SimpleEditorMessage> result = new HashSet<>();
    synchronized (myMessagesLock) {
      result.addAll(myMessages);
    }
    return result;
  }

  public List<SimpleEditorMessage> getMessagesFor(SNode node) {
    List<SimpleEditorMessage> result = new ArrayList<>();
    synchronized (myMessagesLock) {
      result.addAll(myMessagesToNodes.getBySecond(node));
    }
    return result;
  }

  public List<SimpleEditorMessage> getMessagesFor(SNode node, EditorMessageOwner owner) {
    List<SimpleEditorMessage> result = new ArrayList<>();
    synchronized (myMessagesLock) {
      for (SimpleEditorMessage message : myMessagesToNodes.getBySecond(node)) {
        if (message.getOwner() == owner) {
          result.add(message);
        }
      }
    }
    return result;
  }

  public void dispose() {
    assert ThreadUtils.isInEDT() : "dispose() should be called from EDT only";
    myDisposed = true;
    myEditor.getUpdater().removeListener(myRebuildListener);
  }

  private class RebuildMessagesOnEditorUpdate extends UpdaterListenerAdapter {
    @Override
    public void editorUpdated(jetbrains.mps.openapi.editor.EditorComponent editorComponent) {
      assert !myDisposed;
      if (needRebuild()) {
        invalidateMessagesCaches();
        repaintAndRebuildEditorMessages();
      }
    }

    private boolean needRebuild() {
      if (getMessagesCache().isEmpty()) {
        return true;
      }

      for (EditorCell cell : getMessagesCache().keySet()) {
        if (!myEditor.isValid(cell)) {
          return true;
        }
      }

      return false;
    }
  }
}
