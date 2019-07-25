/*
 * Copyright 2003-2011 JetBrains s.r.o.
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
package jetbrains.mps.ide.bookmark;

import jetbrains.mps.ide.bookmark.BookmarkManager.BookmarkListener;
import jetbrains.mps.nodeEditor.DefaultEditorMessage;
import jetbrains.mps.nodeEditor.EditorComponent;
import jetbrains.mps.nodeEditor.EditorMessage;
import jetbrains.mps.nodeEditor.checking.BaseEditorChecker;
import jetbrains.mps.nodeEditor.checking.DisposableEditorChecker;
import jetbrains.mps.nodeEditor.checking.UpdateResult;
import jetbrains.mps.nodeEditor.checking.UpdateResult.Completed;
import jetbrains.mps.util.Cancellable;
import jetbrains.mps.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;

import java.awt.Color;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookmarksHighlighter extends BaseEditorChecker implements DisposableEditorChecker {
  private BookmarkManager myBookmarkManager;
  private boolean myChanged = true;
  private BookmarkListener myListener = new BookmarkListener() {
    @Override
    public void bookmarkAdded(int number, SNode node) {
      myChanged = true;
    }

    @Override
    public void bookmarkRemoved(int number, SNode node) {
      myChanged = true;
    }
  };

  public BookmarksHighlighter(BookmarkManager bookmarkManager) {
    myBookmarkManager = bookmarkManager;
    myBookmarkManager.addBookmarkListener(myListener);
  }

  @Override
  public void dispose() {
    myBookmarkManager.removeBookmarkListener(myListener);
  }

  @Override
  public boolean needsUpdate(EditorComponent editorComponent) {
    return myChanged;
  }

  @NotNull
  @Override
  public UpdateResult update(EditorComponent editorComponent, boolean incremental, boolean applyQuickFixes, Cancellable cancellable) {
    try {
      myChanged = false;
      Set<EditorMessage> result = new HashSet<>();
      List<Pair<SNode, Integer>> bookmarks = myBookmarkManager.getBookmarks(editorComponent.getEditedNode());
      for (Pair<SNode, Integer> bookmark : bookmarks) {
        result.add(new DefaultEditorMessage(bookmark.o1, Color.BLACK, "bookmark " + (bookmark.o2 == -1 ? "" : bookmark.o2), this));
      }
      return new Completed(true, result);
    } catch (RuntimeException e) {
      myChanged = true;
      throw e;
    }
  }
}
