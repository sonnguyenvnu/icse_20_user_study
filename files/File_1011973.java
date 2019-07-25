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
package jetbrains.mps.plugins.actions;

import com.intellij.openapi.actionSystem.Shortcut;
import com.intellij.openapi.keymap.Keymap;
import com.intellij.openapi.keymap.KeymapManager;
import gnu.trove.THashMap;
import gnu.trove.THashSet;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * The basic logic is to replace the default shortcuts with MPS provided during #init, and to revert the changes on #dispose
 */
public abstract class BaseKeymapChanges {
  private static final Logger LOG = LogManager.getLogger(BaseKeymapChanges.class);

  private static final Map<Keymap, Set<String>> ourClearedActions = new THashMap<>();
  private final Map<String, Set<Shortcut>> myRemovedShortcuts = new THashMap<>();

  private final Map<String, Set<ShortcutWrapper>> mySimpleShortcuts = new THashMap<>();

  private final Map<String, Set<ComplexShortcut>> myComplexShortcuts = new THashMap<>();
  private final Map<String, Set<Shortcut>> myAddedComplexShortcuts = new THashMap<>();

  private Keymap myKeymap;

  public abstract String getScheme();

  //shortId is an id w/o parameter ids
  public void parameterizedActionCreated(String shortId, String longId, Object... params) {
    Keymap keymap = getKeymap();
    if (keymap == null) {
      return;
    }

    Set<ComplexShortcut> complexShortcuts = myComplexShortcuts.get(shortId);
    if (complexShortcuts == null) {
      return;
    }

    for (ComplexShortcut cs : complexShortcuts) {
      for (Shortcut s : cs.getShortcutsFor(params)) {
        addShortcutToKeymap(longId, keymap, s);

        Set<Shortcut> added = myAddedComplexShortcuts.get(longId);
        if (added == null) {
          added = new THashSet<>();
          myAddedComplexShortcuts.put(longId, added);
        }
        added.add(s);
      }
    }
  }

  public final void init() {
    Keymap keymap = getKeymap();
    if (keymap == null) {
      return;
    }

    for (Entry<String, Set<ShortcutWrapper>> e : mySimpleShortcuts.entrySet()) {
      String key = e.getKey();
      for (ShortcutWrapper s : e.getValue()) {
        addShortcutToKeymap(key, keymap, s.myShortcut, s.myRemove, s.myReplaceAll);
      }
    }
  }

  public final void dispose() {
    Keymap keymap = getKeymap();
    if (keymap == null) {
      return;
    }

    //complex
    for (Entry<String, Set<Shortcut>> e : myAddedComplexShortcuts.entrySet()) {
      String key = e.getKey();
      for (Shortcut s : e.getValue()) {
        keymap.removeShortcut(key, s);
      }
    }
    myAddedComplexShortcuts.clear();

    //simple
    for (Entry<String, Set<ShortcutWrapper>> e : mySimpleShortcuts.entrySet()) {
      String key = e.getKey();
      for (ShortcutWrapper s : e.getValue()) {
        keymap.removeShortcut(key, s.myShortcut);
      }
    }
    mySimpleShortcuts.clear();

    //register old
    for (Entry<String, Set<Shortcut>> e : myRemovedShortcuts.entrySet()) {
      String key = e.getKey();
      for (Shortcut s : e.getValue()) {
        keymap.addShortcut(key, s);
      }
    }
    myRemovedShortcuts.clear();

    ourClearedActions.clear();
  }

  protected void addSimpleShortcut(String id, ShortcutWrapper... s) {
    Set<ShortcutWrapper> shortcuts = mySimpleShortcuts.get(id);
    if (shortcuts == null) {
      shortcuts = new THashSet<>();
      mySimpleShortcuts.put(id, shortcuts);
    }
    shortcuts.addAll(Arrays.asList(s));
  }

  protected void addSimpleShortcut(String id, Shortcut... s) {
    ShortcutWrapper[] sw = new ShortcutWrapper[s.length];
    for (int i = 0; i < s.length; i++) {
      sw[i] = new ShortcutWrapper(s[i]);
    }
    addSimpleShortcut(id, sw);
  }

  protected void addComplexShortcut(String id, ComplexShortcut... s) {
    Set<ComplexShortcut> shortcuts = myComplexShortcuts.get(id);
    if (shortcuts == null) {
      shortcuts = new THashSet<>();
      myComplexShortcuts.put(id, shortcuts);
    }
    shortcuts.addAll(Arrays.asList(s));
  }

  private void addShortcutToKeymap(String longId, Keymap keymap, Shortcut s) {
    addShortcutToKeymap(longId, keymap, s, false, false);
  }

  private void addShortcutToKeymap(String longId, Keymap keymap, Shortcut s, boolean remove, boolean replaceAll) {
    Shortcut[] oldShortcuts = keymap.getShortcuts(longId);

    boolean isClear = oldShortcuts.length == 0 || ourClearedActions.values().stream().anyMatch(it -> it.contains(longId));

    if (!isClear) {
      myRemovedShortcuts.put(longId, new THashSet<>(Arrays.asList(oldShortcuts)));
      keymap.removeAllActionShortcuts(longId);
    }

    Set<String> actions = ourClearedActions.get(keymap);
    if (actions == null) {
      actions = new THashSet<>();
      ourClearedActions.put(keymap, actions);
    }
    actions.add(longId);

    // Proceed as in class ActionManagerImpl in method processKeyboardShortcutNode
    if (remove) {
      keymap.removeShortcut(longId, s);
    }
    if (replaceAll) {
      keymap.removeAllActionShortcuts(longId);
    }
    if (!remove) {
      keymap.addShortcut(longId, s);
    }
  }

  private Keymap getKeymap() {
    if (myKeymap == null) {
      myKeymap = KeymapManager.getInstance().getKeymap(getScheme());
      if (myKeymap == null) {
        LOG.error("keymap " + getScheme() + " is not found");
        return null;
      }
    }
    return myKeymap;
  }

  protected static abstract class ComplexShortcut {
    public abstract List<Shortcut> getShortcutsFor(Object... params);

    public static class ParameterizedSimpleShortcut extends ComplexShortcut {
      private final List<Shortcut> myShortcut;

      public ParameterizedSimpleShortcut(Shortcut... shortcut) {
        myShortcut = Arrays.asList(shortcut);
      }

      @Override
      public List<Shortcut> getShortcutsFor(Object... params) {
        return myShortcut;
      }
    }
  }

  protected static class ShortcutWrapper {
    public final Shortcut myShortcut;
    public final boolean myRemove;
    public final boolean myReplaceAll;

    public ShortcutWrapper(Shortcut shortcut) {
      this(shortcut, false, false);
    }

    public ShortcutWrapper(Shortcut shortcut, boolean remove, boolean replaceAll) {
      myShortcut = shortcut;
      myRemove = remove;
      myReplaceAll = replaceAll;
    }
  }
}
