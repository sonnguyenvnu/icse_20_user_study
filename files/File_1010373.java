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
package jetbrains.mps.smodel;

import jetbrains.mps.project.ModuleId;
import jetbrains.mps.project.structure.modules.ModuleReference;
import jetbrains.mps.smodel.SModelId.ForeignSModelId;
import jetbrains.mps.smodel.SModelId.ModelNameSModelId;
import jetbrains.mps.util.Computable;
import jetbrains.mps.util.Pair;
import jetbrains.mps.util.StringUtil;
import jetbrains.mps.util.annotation.Hack;
import jetbrains.mps.util.annotation.ToRemove;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.annotations.Immutable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelId;
import org.jetbrains.mps.openapi.model.SModelName;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleId;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade.IncorrectModelReferenceFormatException;

import java.util.Objects;

// FIXME move to [smodel] once dependencies from MPSModuleRepository and SModelRepository are gone
@Immutable
public final class SModelReference implements org.jetbrains.mps.openapi.model.SModelReference {
  private static Logger LOG = Logger.getLogger(SModelReference.class);

  @NotNull
  private final SModelId myModelId;
  @NotNull
  private final SModelName myModelName;
  @Nullable
  public final SModuleReference myModuleReference;

  /**
   * Use of this constructor is discouraged, favor {@link #SModelReference(SModuleReference, SModelId, SModelName)} instead
   */
  public SModelReference(@Nullable SModuleReference module, @NotNull SModelId modelId, @NotNull String modelName) {
    this(module, modelId, new SModelName(modelName));
  }

  public SModelReference(@Nullable SModuleReference module, @NotNull SModelId modelId, @NotNull SModelName modelName) {
    myModelId = modelId;
    myModelName = modelName;
    if (module == null) {
      if (!modelId.isGloballyUnique()) {
        throw new IllegalArgumentException(String.format("Only globally unique model id could be used without module specification: %s", modelId));
      }
    }
    myModuleReference = module;
  }

  @NotNull
  @Override
  public org.jetbrains.mps.openapi.model.SModelId getModelId() {
    return myModelId;
  }

  @NotNull
  @Override
  public SModelName getName() {
    return myModelName;
  }

  @NotNull
  @Override
  public String getModelName() {
    return myModelName.getValue();
  }

  @Nullable
  @Override
  public SModuleReference getModuleReference() {
    return myModuleReference;
  }

  @Override
  public SModel resolve(SRepository repo) {
    if (myModuleReference != null) {
      final SRepository repository;
      if (repo == null) {
        // see StaticReference, which seems to be the only place we pass null as repository
        repository = MPSModuleRepository.getInstance();
      } else {
        repository = repo;
      }
      Computable<SModel> c = () -> {
        SModule module = repository.getModule(myModuleReference.getModuleId());
        if (module == null) {
          return null;
        }
        return module.getModel(myModelId);
      };
      if (!repository.getModelAccess().canRead()) {
        LOG.warn("Attempt to resolve a model not from read action. What are you going to do with return value? Hint: at least, read. Please ensure proper model access then.", new Throwable());
        return new ModelAccessHelper(repository).runReadAction(c);
      } else {
        return c.compute();
      }
    }

    // FIXME !!! use supplied SRepository, not global model repo !!!
    // If there's no module reference, and model id is global, it's supposed we shall get the model from a global repository.
    // However, at the moment, there's no easy way to get model from SRepository (other than to iterate over all modules and models,
    // which doesn't sound like a good approach). Either shall provide method to find model from SRepository, or drop
    // 'globally unique' model id altogether. What's the benefit of them?

    // NOTE, shall tolerate null repo unless every single piece of code that expects StaticReference of a newly created node
    // hanging in the air to resolve. @see StaticReference#getTargetSModel
    if (SModelRepository.getInstance() != null) {
      // could be null in tests
      return SModelRepository.getInstance().getModelDescriptor(this);
    }
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (o instanceof SModelReference) {
      SModelReference that = (SModelReference) o;
      if (myModelId.equals(that.myModelId)) {
        if (myModelId.isGloballyUnique() && that.myModelId.isGloballyUnique()) {
          return true;
        }
        return Objects.equals(getModuleReference(), that.getModuleReference());
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = myModelId.hashCode();
    // It's vital not to take module reference into account for models with globally unique ids as we need to match (e.g. in map keys)
    // model references in both formats (with and without module identity part).
    if (!myModelId.isGloballyUnique()) {
      result += 31 * getModuleReference().hashCode();
    }
    return result;
  }

  /**
   * @deprecated This code shall move to private method of PersistenceRegistry, which would dispatch to proper
   *   registered factories. Use {@link PersistenceFacade#createModelReference(String)} instead.
   * Format: <code>[ moduleID / ] modelID [ ([moduleName /] modelName ) ]</code>
   */
  @NotNull
  @Deprecated
  @ToRemove(version = 3.3)
  public static SModelReference parseReference(String s) {
    Pair<Pair<SModuleId, String>, Pair<SModelId, String>> parseResult = parseReference_internal(s);
    SModuleId moduleId = parseResult.o1.o1;
    String moduleName = parseResult.o1.o2;
    SModelId modelId = parseResult.o2.o1;
    String modelName = parseResult.o2.o2;
    SModuleReference moduleRef =
        moduleId != null || moduleName != null ? new jetbrains.mps.project.structure.modules.ModuleReference(moduleName, moduleId) : null;
    return new SModelReference(moduleRef, modelId, modelName);
  }

  @Contract(value = "null->null")
  public static Pair<Pair<SModuleId, String>, Pair<SModelId, String>> parseReference_internal(@Nullable String s) {
    if (s == null) return null;
    s = s.trim();
    int lParen = s.indexOf('(');
    int rParen = s.lastIndexOf(')');
    String presentationPart = null;
    if (lParen > 0 && rParen == s.length() - 1) {
      presentationPart = s.substring(lParen + 1, rParen);
      s = s.substring(0, lParen);
      lParen = s.indexOf('(');
      rParen = s.lastIndexOf(')');
    }
    if (lParen != -1 || rParen != -1) {
      throw new IncorrectModelReferenceFormatException("parentheses do not match in: `" + s + "'");
    }

    SModuleId moduleId = null;
    int slash = s.indexOf('/');
    if (slash >= 0) {
      // FIXME I wonder why there's no SModuleIdFactory and corresponding methods in PersistenceFacade
      try {
        moduleId = ModuleId.fromString(StringUtil.unescapeRefChars(s.substring(0, slash)));
      } catch (IllegalArgumentException e) {
        throw new IncorrectModelReferenceFormatException("Could not parse module id from the string " + s, e);
      }
      s = s.substring(slash + 1);
    }

    String modelIDString = StringUtil.unescapeRefChars(s);
    SModelId modelId;
    if (modelIDString.indexOf(':') >= 0) {
      PersistenceFacade facade = PersistenceFacade.getInstance();
      // temporary: SModelReference can be created without active PersistenceFacade
      if (facade == null) {
        // FIXME get rid of facade == null case, if any
        // Besides, shall move the code to PersistenceRegistry, as it's responsible for prefixes and factory pick
        LOG.warn("Please report stacktrace, which would help us to find out improper MPS initialization sequence", new Throwable());
      }
      modelId = facade != null
          ? facade.createModelId(modelIDString)
          : jetbrains.mps.smodel.SModelId.fromString(modelIDString);
    } else {
      // dead code? I suspect ModelNameSModelId, if any, would start with "m:" prefix and we'd never get into else clause
      // OTOH, there seems to be a special hack in toString(), that persists ModelNameSModelId without the prefix
      modelId = new ModelNameSModelId(modelIDString);
    }

    String moduleName = null;
    String modelName = null;
    if (presentationPart != null) {
      slash = presentationPart.indexOf('/');
      if (slash >= 0) {
        moduleName = StringUtil.unescapeRefChars(presentationPart.substring(0, slash));
        modelName = StringUtil.unescapeRefChars(presentationPart.substring(slash + 1));
      } else {
        modelName = StringUtil.unescapeRefChars(presentationPart);
      }
    }

    if (modelName == null || modelName.isEmpty()) {
      modelName = modelId.getModelName();
      if (modelName == null) {
        throw new IncorrectModelReferenceFormatException("Incomplete model reference, the presentation part is absent");
      }
    }

    if (moduleId == null) {
      moduleId = extractModuleIdFromModelIdIfJavaStub(modelId);
    }

    if (isLegacyJavaStubModelId(modelId)) {
      modelId = newJavaPackageStubFromLegacy(modelId);
    }

    return new Pair<>(new Pair<>(moduleId, moduleName), new Pair<>(modelId, modelName));
  }

  /**
   * This temporary code suites the purpose to homogenize java_stub model references, that used
   * to be kept in two different formats (one is "module id/model id including module id/(module name/model name)"
   * and another "model id including module id(module name/model name)". If there's module id anyway, why
   * would anyone keep it to model id then, and common patter for model reference (with module id coming first) shall be used.
   *
   * Once all model references to java stub are updated, this code shall cease to exist.
   *
   * IMPORTANT: there's a fly in the ointment, though - we shall read references of old models, and thus shall keep this code
   * forever. Perhaps, we can move it into persistence/vcs modules and bury it there? Another alternative is to introduce
   * new model identity to replace 'f:' identity, and leave dedicated SModelId factory for the legacy support in vcs/persistence only.
   */
  @ToRemove(version = 3.3)
  @Nullable
  @Hack
  private static SModuleId extractModuleIdFromModelIdIfJavaStub(SModelId modelId) {
    if (isVerboseJavaStubModelId(modelId)) {
      String idValue = ((ForeignSModelId) modelId).getId();
      String stereo = SModelStereotype.getStubStereotypeForId(LanguageID.JAVA);
      if (idValue.length() > stereo.length() + 2 && idValue.startsWith(stereo) && idValue.charAt(stereo.length()) == '#') {
        // two forms of legacy stub model id:
        //    f:java_stub#module id#package name
        //    f:java_stub#package name
        int secondHashIndex = idValue.indexOf('#', stereo.length() + 1);
        // there are two hash chars and non-empty package name
        if (secondHashIndex != -1 && idValue.length() > secondHashIndex) {
          return ModuleId.fromString(idValue.substring(stereo.length()+1, secondHashIndex));
        }
      }
    }
    return null;
  }

  /**
   * IMPORTANT: see {@link #extractModuleIdFromModelIdIfJavaStub(SModelId)} for the reasons we didn't remove it.
   *
   * Compatibility code to migrate stub model id with module id to an 'honest' model id without module id.
   *
   * @return <code>true</code> if it's model id of java stub and it includes module id as it used to do in MPS 3.2 and earlier
   */
  @Deprecated
  @ToRemove(version = 3.3)
  private static boolean isVerboseJavaStubModelId(SModelId id) {
    if (ForeignSModelId.TYPE.equals(id.getType()) && id instanceof ForeignSModelId) {
      String idValue = ((ForeignSModelId) id).getId();
      String stereo = SModelStereotype.getStubStereotypeForId(LanguageID.JAVA);
      if (idValue.length() > stereo.length() + 2 && idValue.startsWith(stereo) && idValue.charAt(stereo.length()) == '#') {
        // legacy stub model id: f:java_stub#module id#package name
        //    new stub model id: f:java_stub#package name
        int secondHashIndex = idValue.indexOf('#', stereo.length() + 1);
        // there are two hash chars and non-empty package name
        return secondHashIndex != -1 && idValue.length() > secondHashIndex;
      }
    }
    return false;
  }

  /**
   * IMPORTANT: see {@link #extractModuleIdFromModelIdIfJavaStub(SModelId)} for the reasons we didn't remove it.
   * @return <code>true</code> if it's model id of java stub in its legacy form (i.e. foreign, f:java_stub#...), either with or without module id part.
   */
  @Deprecated
  @ToRemove(version = 3.3)
  private static boolean isLegacyJavaStubModelId(SModelId id) {
    if (ForeignSModelId.TYPE.equals(id.getType()) && id instanceof ForeignSModelId) {
      String idValue = ((ForeignSModelId) id).getId();
      String stereo = SModelStereotype.getStubStereotypeForId(LanguageID.JAVA);
      return (idValue.length() > stereo.length() + 2 && idValue.startsWith(stereo) && idValue.charAt(stereo.length()) == '#');
    }
    return false;
  }

  /**
   * Here we duplicate code of JavaPackageNameStub, not to introduce dependency to [java-stub] module
   */
  @ToRemove(version = 3.3)
  @Hack
  private static SModelId newJavaPackageStubFromLegacy(SModelId id) {
    // pre: isLegacyJavaStubModel()
    String idValue = ((ForeignSModelId) id).getId();
    int lastHash = idValue.lastIndexOf('#');
    return PersistenceFacade.getInstance().createModelId(LanguageID.JAVA + ':' + idValue.substring(lastHash + 1));
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    if (getModuleReference() != null) {
      sb.append(StringUtil.escapeRefChars(getModuleReference().getModuleId().toString()));
      sb.append('/');
    }

    String modelId = myModelId instanceof ModelNameSModelId ? myModelId.getModelName() : myModelId.toString();
    sb.append(StringUtil.escapeRefChars(modelId));

    if (getModuleReference() == null && myModelName.getValue().equals(myModelId.getModelName())) {
      return sb.toString();
    }

    sb.append('(');
    if (getModuleReference() != null && getModuleReference().getModuleName() != null) {
      sb.append(StringUtil.escapeRefChars(getModuleReference().getModuleName()));
      sb.append('/');
    }
    if (!myModelName.getValue().equals(myModelId.getModelName())) {
      // no reason to write down model name if it's part of module id
      sb.append(StringUtil.escapeRefChars(myModelName.getValue()));
    }
    sb.append(')');
    return sb.toString();
  }

  /**
   * @see jetbrains.mps.project.structure.modules.ModuleReference#differs(SModuleReference, SModuleReference)
   */
  public static boolean differs(org.jetbrains.mps.openapi.model.SModelReference ref1, org.jetbrains.mps.openapi.model.SModelReference ref2) {
    if (ref1 == null || ref2 == null) {
      return ref1 != ref2;
    }
    if (ModuleReference.differs(ref1.getModuleReference(), ref2.getModuleReference())) {
      return true;
    }
    return !(Objects.equals(ref1.getModelId(), ref2.getModelId()) && Objects.equals(ref1.getModelName(), ref2.getModelName()));
  }
}
