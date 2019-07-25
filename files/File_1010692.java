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
package jetbrains.mps.vfs.refresh;

import org.jetbrains.annotations.NotNull;

/**
 * Options of file-system notifications filtering
 */
public final class FileListeningPreferences {
  public final boolean notifyOnChildCreation;
  public final boolean notifyOnParentCreation;
  public final boolean notifyOnChildChange;
  public final boolean notifyOnParentChange;
  public final boolean notifyOnChildRemoval;
  public final boolean notifyOnParentRemoval;

  private FileListeningPreferences(boolean notifyOnChildCreation,
                                   boolean notifyOnParentCreation,
                                   boolean notifyOnChildChange,
                                   boolean notifyOnParentChange,
                                   boolean notifyOnChildRemoval,
                                   boolean notifyOnParentRemoval) {
    this.notifyOnChildCreation = notifyOnChildCreation;
    this.notifyOnParentCreation = notifyOnParentCreation;
    this.notifyOnChildChange = notifyOnChildChange;
    this.notifyOnParentChange = notifyOnParentChange;
    this.notifyOnChildRemoval = notifyOnChildRemoval;
    this.notifyOnParentRemoval = notifyOnParentRemoval;
  }

  public static PreferencesBuilder construct() {
    return new PreferencesBuilder();
  }

  public static final class PreferencesBuilder {
    private boolean myNotifyOnChildCreation = false;
    private boolean myNotifyOnParentCreation = false;
    private boolean myNotifyOnChildChange = false;
    private boolean myNotifyOnParentChange = false;
    private boolean myNotifyOnChildRemoval = false;
    private boolean myNotifyOnParentRemoval = false;

    private PreferencesBuilder() {
    }

    /**
     * a listener attached to the directory 'solutions' will receive the notification for a creation of the 'solutions/s1.msd'
     */
    public PreferencesBuilder notifyOnDescendantCreation() {
      myNotifyOnChildCreation = true;
      return this;
    }

    /**
     * a listener attached to the file 's1/models/a.mps' will receive the notification for a creation of the 's1/models' directory
     * NOTE: no guarantee that the mentioned file exists (!)
     */
    public PreferencesBuilder notifyOnAncestorCreation() {
      myNotifyOnParentCreation = true;
      return this;
    }

    /**
     * a listener attached to the directory 's1/models' will receive the notification for a change of the 's1/models/a.mps' file
     */
    public PreferencesBuilder notifyOnDescendantChange() {
      myNotifyOnChildChange = true;
      return this;
    }

    /**
     * a listener attached to the file 's1/models/a.mps' will receive the notification for a change of the 's1/models' directory
     * fixme does it make any sense? -- probably
     */
    public PreferencesBuilder notifyOnAncestorChange() {
      myNotifyOnParentChange = true;
      return this;
    }

    /**
     * a listener attached to the directory 's1/models' will receive the notification for a removal of the 's1/models/a.mps' file
     */
    public PreferencesBuilder notifyOnDescendantRemoval() {
      myNotifyOnChildRemoval = true;
      return this;
    }

    /**
     * a listener attached to the file 's1/models/a.mps' will receive the notification for a removal of the 's1/models' directory
     */
    public PreferencesBuilder notifyOnParentRemoval() {
      myNotifyOnParentRemoval = true;
      return this;
    }

    @NotNull
    public FileListeningPreferences build() {
      return new FileListeningPreferences(myNotifyOnChildCreation,
                                          myNotifyOnParentCreation,
                                          myNotifyOnChildChange,
                                          myNotifyOnParentChange,
                                          myNotifyOnChildRemoval,
                                          myNotifyOnParentRemoval);
    }
  }
}
