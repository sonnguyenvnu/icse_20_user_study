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
package jetbrains.mps.smodel.adapter.ids;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.annotations.Immutable;

@Immutable
public final class SConceptId extends SElementId {

  public SConceptId(@NotNull SLanguageId languageId, long conceptId) {
    super(languageId, conceptId);
  }

  public static SConceptId deserialize(String s) {
    return deserializeDefault(s, SConceptId::new);
  }

  // FIXME all these method overrides needed for compatibility with MPS baseLanguage code that is referencing these methods.
  // If we remove these overrides, we got broken references in MPS
  // Generally speaking, moving method up in hierarchy is compatible in pure java, but it is not compatible in MPS
  // We should figure out how to handle this scenario with migrations, unless leave these overrides here. See MPS-28629

  @NotNull
  public SLanguageId getLanguageId() {
    return super.getLanguageId();
  }

  public long getIdValue() {
    return super.getIdValue();
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  public String serialize() {
    return super.serialize();
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
