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
package jetbrains.mps.smodel;

import jetbrains.mps.util.InternUtil;
import jetbrains.mps.util.annotation.ToRemove;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.annotations.Immutable;

import java.util.UUID;

public abstract class SModelId implements org.jetbrains.mps.openapi.model.SModelId {
  private static final String REGULAR_PREFIX = "r:";
  private static final String FOREIGN_PREFIX = "f:";

  public static SModelId generate() {
    return new RegularSModelId(UUID.randomUUID());
  }

  public static SModelId regular(UUID uid) {
    return new RegularSModelId(uid);
  }

  public static SModelId regular(String suffix) {
    try {
      UUID uuid = UUID.fromString(suffix);
      return regular(uuid);
    } catch (IllegalArgumentException e) {
      long lower = Long.parseLong(suffix);
      UUID uuid = new UUID(0x0000000000004000, lower);
      return regular(uuid);
    }
  }

  public static SModelId foreign(@NotNull String id) {
    return new ForeignSModelId(id);
  }

  /**
   * See {@link jetbrains.mps.smodel.SModelId.ForeignSModelId} for details
   */
  public static SModelId foreign(@Nullable String kind, @NotNull String id) {
    if (kind != null && !kind.trim().isEmpty()) {
      return new ForeignSModelId(kind.trim() + "#" + id);
    } else {
      return new ForeignSModelId(id);
    }
  }

  /**
   * @deprecated this method doesn't support {@link org.jetbrains.mps.openapi.persistence.SModelIdFactory},
   * use {@link org.jetbrains.mps.openapi.persistence.PersistenceFacade#createModelId(String)} instead.
   */
  @Deprecated
  @ToRemove(version = 3.3)
  public static SModelId fromString(String id) {
    if (id.startsWith(REGULAR_PREFIX)) {
      String suffix = id.substring(REGULAR_PREFIX.length());
      return regular(suffix);
    }
    if (id.startsWith(FOREIGN_PREFIX)) {
      String suffix = id.substring(FOREIGN_PREFIX.length());
      return foreign(suffix);
    }
    if (id.startsWith(RelativePathSModelId.TYPE + ":")) {
      return new RelativePathSModelId(id.substring(1 + RelativePathSModelId.TYPE.length()));
    }
    throw new IllegalArgumentException("wrong id " + id);
  }

  private SModelId() {
  }

  @Override
  public boolean isGloballyUnique() {
    return true;
  }

  @Override
  public String getModelName() {
    return null;
  }

  @Immutable
  public final static class RegularSModelId extends SModelId {
    public static final String TYPE = "r";

    private final UUID myId;

    /*package*/ RegularSModelId(UUID id) {
      myId = id;
    }

    public UUID getId() {
      return myId;
    }

    public boolean equals(Object obj) {
      if (!(obj instanceof RegularSModelId)) return false;
      return ((RegularSModelId) obj).myId.equals(myId);
    }

    public int hashCode() {
      return myId.hashCode();
    }

    @Override
    public String getType() {
      return TYPE;
    }

    public String toString() {
      return REGULAR_PREFIX + myId;
    }
  }

  /**
   * Model identity based on plain string, with optional kind part up to first hash ('#') sign (i.e. "[kind#]identity").
   * Almost any string could be used for identity, provided it's unique within a repository.
   * Although there's no known restrictions about string except uniqueness at the moment, it's advised not to stretch this freedom too much.
   * Primary difference with {@link jetbrains.mps.smodel.SModelId.ModelNameSModelId} is that identity is not treated as model name
   *
   * IMPORTANT: it's advised not to use this kind of model id and leave it for legacy code (e.g. VCS that reads model in old persistence format).
   *            This one has misguiding name (foreign to what?), mandates globally uniqueness while doesn't help to achieve one.
   *            Consider use of {@link jetbrains.mps.smodel.SModelId.IntegerSModelId} if you need simple model id.
   */
  @Immutable
  public final static class ForeignSModelId extends SModelId {
    public static final String TYPE = "f";
    private final String myId;

    /*package*/ ForeignSModelId(String id) {
      myId = InternUtil.intern(id);
    }

    /**
     * @return never <code>null</code>
     */
    public String getId() {
      return myId;
    }

    /**
     * @return optional part of the identity string, up to first '#', excluding; empty string if no kind part found
     */
    public String getKind() {
      // What's the point of this ForeignSModelId then if we don't expose 'kind', provided we've specified one at creation time?
      // It could be plain AnyStringModelId then.
      int x = myId.indexOf('#');
      return x == -1 ? "" : myId.substring(0, x);
    }

    public boolean equals(Object obj) {
      if (!(obj instanceof ForeignSModelId)) return false;
      return ((ForeignSModelId) obj).myId.equals(myId);
    }

    public int hashCode() {
      return myId.hashCode();
    }

    @Override
    public String getType() {
      return TYPE;
    }

    public String toString() {
      return FOREIGN_PREFIX + myId;
    }
  }

  @Immutable
  public final static class ModelNameSModelId extends SModelId {
    public static final String TYPE = "m";
    private final String myModelName;

    public ModelNameSModelId(String modelName) {
      super();
      myModelName = modelName;
    }

    @Override
    public String getModelName() {
      return myModelName;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      return myModelName.equals(((ModelNameSModelId) o).myModelName);
    }

    @Override
    public int hashCode() {
      return myModelName.hashCode();
    }

    @Override
    public String getType() {
      return TYPE;
    }

    public String toString() {
      return TYPE + ":" + myModelName;
    }
  }

  @Immutable
  public final static class RelativePathSModelId extends SModelId {
    public static final String TYPE = "path";
    private final String myPath;

    public RelativePathSModelId(String path) {
      super();
      myPath = path;
    }

    @Override
    public String getModelName() {
      return myPath;
    }

    public String getFileName() {
      int i = myPath.lastIndexOf('/');
      if (i >= 0 && i + 1 < myPath.length()) {
        return myPath.substring(i + 1);
      }
      return myPath;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      return myPath.equals(((RelativePathSModelId) o).myPath);
    }

    @Override
    public int hashCode() {
      return myPath.hashCode();
    }

    @Override
    public String getType() {
      return TYPE;
    }

    public String toString() {
      return TYPE + ":" + myPath;
    }
  }

  /**
   * Integer-backed, module-private model identity.
   * MPS reserves values in range [0x0F000000..0xFFFFFFFF] for own uses, you're free to use lower range.
   */
  @Immutable
  public static final class IntegerSModelId extends SModelId {
    public static final String TYPE = "i";
    private final int myValue;

    public IntegerSModelId(int value) {
      myValue = value;
    }

    public int getValue() {
      return myValue;
    }

    @Override
    public boolean isGloballyUnique() {
      return false;
    }

    @Override
    public int hashCode() {
      return myValue;
    }

    @Override
    public boolean equals(Object obj) {
      return obj instanceof IntegerSModelId && ((IntegerSModelId) obj).myValue == myValue;
    }

    @Override
    public String getType() {
      return TYPE;
    }

    @Override
    public String toString() {
      return String.format("%s:%04x", TYPE, myValue);
    }

    public static IntegerSModelId parse(String cs) throws IllegalArgumentException {
      return new IntegerSModelId(Integer.parseInt(cs, 16));
    }
  }
}
