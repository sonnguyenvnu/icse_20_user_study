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
package jetbrains.mps.text.impl;

import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.smodel.ModelDependencyScanner;
import jetbrains.mps.smodel.SNodeUtil;
import jetbrains.mps.smodel.language.LanguageRegistry;
import jetbrains.mps.smodel.language.LanguageRegistryListener;
import jetbrains.mps.smodel.language.LanguageRuntime;
import jetbrains.mps.text.MissingTextGenDescriptor;
import jetbrains.mps.text.rt.TextGenAspectDescriptor;
import jetbrains.mps.text.rt.TextGenDescriptor;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.util.ImmediateParentConceptIterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Excerpt from ConceptRegistry related to TextGenDescriptor.
 * It's artifact of refactoring to break [textgen] and [kernel] cycle dependency.
 * FIXME For the time being, it's initialized together with ConceptRegistry from MPSCore, though shall be separate ComponentPlugin,
 * like MPSGenerator, and initialized from MPSCoreComponents and alike.
 * @author Artem Tikhomirov
 * @since 3.3
 */
public class TextGenRegistry implements CoreComponent, LanguageRegistryListener {
  private static TextGenRegistry INSTANCE;

  private final Map<String, TextGenDescriptor> textGenDescriptors = new ConcurrentHashMap<>();
  private final LanguageRegistry myLanguageRegistry;

  /*package*/ TextGenRegistry(@NotNull LanguageRegistry languageRegistry) {
    myLanguageRegistry = languageRegistry;
  }

  @Override
  public void init() {
    if (INSTANCE != null) {
      throw new IllegalStateException("double initialization");
    }
    INSTANCE = this;
    myLanguageRegistry.addRegistryListener(this);
  }

  @Override
  public void dispose() {
    myLanguageRegistry.removeRegistryListener(this);
    INSTANCE = null;
  }

  public static TextGenRegistry getInstance() {
    return INSTANCE;
  }

  /**
   * @param node
   * @return <code>true</code> if there's a TextGen for the node
   */
  public boolean hasTextGen(@NotNull SNode node) {
    return !(getTextGenDescriptor(node) instanceof MissingTextGenDescriptor);
  }

  @NotNull
  public TextGenDescriptor getTextGenDescriptor(@Nullable SNode node) {
    if (node == null) {
      // FIXME default implementation doesn't expect null node
      return new MissingTextGenDescriptor();
    }
    return getTextGenDescriptor(node.getConcept());
  }

  private TextGenDescriptor getTextGenDescriptor(SConcept concept) {
    final String fqName = concept.getQualifiedName();

    TextGenDescriptor descriptor = textGenDescriptors.get(fqName);

    if (descriptor != null) {
      return descriptor;
    }

    // Would be nice if TGAD could answer for any subtype from the same language, i.e. when there's TextGen for A,
    // and there's B extends A, and we ask for B's textgen, TGAD might answer with A's right away. Then, we could
    // ask each language only once
    // TODO  HashSet<SLanguage> seen = new HashSet<SLanguage>();
    for (SConcept next : new ImmediateParentConceptIterator(concept, SNodeUtil.concept_BaseConcept)) {
      TextGenAspectDescriptor textGenAspectDescriptor = getAspect(next);
      if (textGenAspectDescriptor == null) {
        continue;
      }
      descriptor = textGenAspectDescriptor.getDescriptor(next);
      if (descriptor != null) {
        break;
      }
    }

    if (descriptor == null) {
      descriptor = new MissingTextGenDescriptor();
    }

    textGenDescriptors.put(fqName, descriptor);

    return descriptor;
  }

  @Nullable
  private TextGenAspectDescriptor getAspect(SConcept concept) {
    LanguageRuntime languageRuntime = myLanguageRegistry.getLanguage(concept.getLanguage());
    if (languageRuntime == null) {
      // Then language was just renamed and was not re-generated then it can happen that it has no
      Logger.getLogger(TextGenRegistry.class).warn(String.format("No language for concept %s, while looking for textgen descriptor.", concept));
      return null;
    } else {
      return languageRuntime.getAspect(TextGenAspectDescriptor.class);
    }
  }

  /**
   * @param model model to generate text from
   * @return aspect runtime instances for all languages involved
   */
  @NotNull
  public Collection<TextGenAspectDescriptor> getAspects(@NotNull SModel model) {
    // FIXME likely, shall collect all extended languages as well, as there might be instances of a language without textgen in the model,
    // while textgen elements are derived from extended language. HOWEVER, need to process breakdownToTextUnits carefully, so that default
    // file-per-root breakdown doesn't create duplicates!
    ArrayList<TextGenAspectDescriptor> rv = new ArrayList<>(5);
    final ModelDependencyScanner modelScanner = new ModelDependencyScanner();
    modelScanner.crossModelReferences(false).usedLanguages(true).walk(model);
    for (SLanguage l : modelScanner.getUsedLanguages()) {
      final LanguageRuntime lr = myLanguageRegistry.getLanguage(l);
      if (lr == null) {
        // XXX shall report missing language?
        continue;
      }
      final TextGenAspectDescriptor rtAspect = lr.getAspect(TextGenAspectDescriptor.class);
      if (rtAspect != null) {
        rv.add(rtAspect);
      }
    }
    return rv;
  }

  @Override
  public void beforeLanguagesUnloaded(Iterable<LanguageRuntime> languages) {
    // @see ConceptRegistry#beforeLanguagesUnloaded
  }

  @Override
  public void afterLanguagesLoaded(Iterable<LanguageRuntime> languages) {
    textGenDescriptors.clear();
  }

}
