/*
 * Copyright 2003-2015 JetBrains s.r.o.
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
package jetbrains.mps.smodel.adapter.structure.language;

import jetbrains.mps.smodel.Language;
import jetbrains.mps.smodel.adapter.structure.FormatException;
import jetbrains.mps.smodel.language.LanguageRuntime;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.module.SModuleReference;

/**
 * See InvalidConcept doc
 */
public final class InvalidLanguage extends SLanguageAdapter {
  public static final java.lang.String INVALID_PREFIX = "invalid";

  public InvalidLanguage(@NotNull String fqName) {
    super(fqName);
  }

  @Override
  @NotNull
  public String getQualifiedName() {
      return myLanguageFqName;
  }

  @Override
  @Nullable
  public LanguageRuntime getLanguageDescriptor() {
    return null;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof InvalidLanguage)) return false;
    String otherId = ((InvalidLanguage) obj).myLanguageFqName;
    return myLanguageFqName.equals(otherId);
  }

  @Override
  public int hashCode() {
    return myLanguageFqName.hashCode();
  }

  @Override
  @Nullable
  public Language getSourceModule() {
    return null;
  }

  @Override
  public SModuleReference getSourceModuleReference() {
    return null;
  }

  @Override
  public String serialize() {
    return INVALID_PREFIX + ID_DELIM + myLanguageFqName;
  }

  public static InvalidLanguage deserialize(String s) {
    String marker = INVALID_PREFIX + ID_DELIM;
    if (!s.startsWith(marker)) {
      throw new FormatException("Invalid language should have prefix " + marker + ":" + s);
    }
    return new InvalidLanguage(s.substring(marker.length()));
  }
}
