package com.nao20010128nao.bindingviewholder.processor

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("java.lang.SuppressWarnings")
class Processor : AbstractProcessor() {
    override fun process(types: MutableSet<out TypeElement>?, round: RoundEnvironment?): Boolean {
        round!!
        types!!
        val dataBinding=processingEnv!!.elementUtils!!.getPackageElement("android.databinding.ViewDataBinding")!!
        round.getElementsAnnotatedWith(SuppressWarnings::class.java)
                .filter { it.kind==ElementKind.CLASS }
                .map { it as TypeElement }
                .filter { it.superclass.equals(dataBinding) }
                .forEach { println(it) }
        return true
    }
}