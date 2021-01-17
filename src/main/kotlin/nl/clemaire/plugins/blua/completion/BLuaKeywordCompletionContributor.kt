package nl.clemaire.plugins.blua.completion

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder.create
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.PlatformPatterns.psiFile
import com.intellij.patterns.StandardPatterns.or
import com.intellij.util.ProcessingContext
import nl.clemaire.plugins.blua.ast.BLuaTypes

class BLuaKeywordCompletionContributor : CompletionContributor() {
    companion object {
        private val inFunBodyP = psiElement().withAncestor(64,
            or(psiElement(BLuaTypes.NAMED_FUNCTION_STMT), psiElement(BLuaTypes.FUNCTION_EXPR)))
        private val inFileP = psiElement()

        private val fbKeywordCompletion = provider { params, context, results ->
            results.addElement(create("return"))
        }

        private val stmtKeywordCompletion = provider { _, _, results ->
            results.addAllElements(listOf(
                create("function"),
                create("local"),

                create("if"),
                create("then"),
                create("elseif"),
                create("else"),

                create("do"),
                create("while"),
                create("for"),
                create("in"),

                create("repeat"),
                create("until"),

                create("true"),
                create("false"),
            ))
        }

        private fun provider(f: (CompletionParameters, ProcessingContext, CompletionResultSet) -> Unit): CompletionProvider<CompletionParameters> =
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    f(parameters, context, result)
                }
            }
    }

    init {
        extend(CompletionType.BASIC, inFileP, stmtKeywordCompletion)
        extend(CompletionType.BASIC, inFunBodyP, fbKeywordCompletion)
    }
}
