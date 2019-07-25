/*
 * Copyright 2003-2014 JetBrains s.r.o.
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
package jetbrains.mps.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.annotations.Immutable;
import org.jetbrains.mps.openapi.module.SModuleId;

import java.util.UUID;

@Immutable
public abstract class ModuleId implements SModuleId {
  private static final char NAME_ID_PREFIX = '~';

  public static ModuleId regular() {
    return new Regular(UUID.randomUUID());
  }

  public static ModuleId regular(UUID uuid) {
    return new Regular(uuid);
  }

  public static ModuleId foreign(String name) {
    return new Foreign(name);
  }

  public static ModuleId fromString(@NotNull String text) {
    if (text.charAt(0) == NAME_ID_PREFIX) {
      return new Foreign(text.substring(1));
    } else {
      return new Regular(UUID.fromString(text));
    }
  }

  public static class Regular extends ModuleId {
    private final UUID myUid;

    private Regular(UUID uid) {
      myUid = uid;
    }

    public boolean equals(Object obj) {
      if (!(obj instanceof Regular)) return false;

      Regular id = (Regular) obj;
      return id.myUid.equals(myUid);
    }

    public int hashCode() {
      return myUid.hashCode();
    }

    public String toString() {
      return myUid.toString();
    }

    public UUID getUUID() {
      return myUid;
    }
  }

  public static class Foreign extends ModuleId {
    private final String myName;

    private Foreign(String name) {
      myName = name;
    }

    public boolean equals(Object obj) {
      if (!(obj instanceof Foreign)) return false;

      Foreign id = (Foreign) obj;
      return id.myName.equals(myName);
    }

    public String getName() {
      return myName;
    }

    public int hashCode() {
      return myName.hashCode();
    }

    public String toString() {
      return NAME_ID_PREFIX + myName;
    }
  }
}
