/**
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.javaoperatorsdk.quarkus.extension.deployment;


import io.quarkus.gizmo.Gizmo;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;

/**
 * @author <a href="claprun@redhat.com">Christophe Laprun</a>
 */
public class CustomResourceClassVisitor extends ClassVisitor implements Opcodes {

  private final ClassWriter classWriter = new ClassWriter(0);

  public CustomResourceClassVisitor(String className, String specClassName, String statusClassName) {
    super(Gizmo.ASM_API_VERSION);

    final var replacedClassName = className.replace('.', '/');
    classWriter.visit(V11, ACC_PUBLIC | ACC_SUPER,
        replacedClassName + "_Generated",
        null,
        replacedClassName, null);
  }

  @Override
  public void visit(int version, int access, String name, String signature, String superName,
      String[] interfaces) {
    super.visit(version, access, name, signature, superName, interfaces);

    final var methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
    methodVisitor.visitCode();
    Label label0 = new Label();
    methodVisitor.visitLabel(label0);
    methodVisitor.visitLineNumber(20, label0);
    methodVisitor.visitVarInsn(ALOAD, 0);
    methodVisitor
        .visitMethodInsn(INVOKESPECIAL, "io/javaoperatorsdk/operator/sample/CustomService",
            "<init>", "()V", false);
    methodVisitor.visitInsn(RETURN);
    Label label1 = new Label();
    methodVisitor.visitLabel(label1);
    methodVisitor
        .visitLocalVariable("this", "Lio/javaoperatorsdk/operator/sample/OptimizedCustomService;",
            null, label0, label1, 0);
    methodVisitor.visitMaxs(1, 1);
    methodVisitor.visitEnd();

  }
}
