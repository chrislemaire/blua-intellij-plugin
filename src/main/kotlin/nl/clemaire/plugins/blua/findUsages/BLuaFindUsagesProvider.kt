package nl.clemaire.plugins.blua.findUsages

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.tree.TokenSet
import com.intellij.psi.util.elementType
import nl.clemaire.plugins.blua.ast.*
import nl.clemaire.plugins.blua.ast.BLuaTypes.*
import nl.clemaire.plugins.blua.parser.BLuaLexerAdapter

class BLuaFindUsagesProvider : FindUsagesProvider {
    override fun getWordsScanner(): WordsScanner =
        DefaultWordsScanner(
            BLuaLexerAdapter(),
            TokenSet.create(ID_TOKEN),
            TokenSet.create(BLOCK_COMMENT, LINE_COMMENT),
            TokenSet.create(STRING_EXPR, INT_EXPR, DOUBLE_EXPR, BOOL_EXPR)
        )

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
        // Find usages for named functions and class declarations
        if (psiElement.elementType == ID_TOKEN &&  psiElement.parent is BLuaNamedFunctionStmt)
            return (psiElement.parent as PsiNameIdentifierOwner).nameIdentifier == psiElement

        // Find usages for parameters
        return true
    }

    override fun getHelpId(psiElement: PsiElement): String? =
        null

    override fun getType(element: PsiElement): String =
        when (element) {
            is BLuaClassDeclStmt -> "class"
            is BLuaNamedFunctionStmt -> "function"
            is BLuaParam -> "parameter"
            is BLuaField -> "field"
            is BLuaTypedId -> "variable"
            else -> ""
        }

    override fun getDescriptiveName(element: PsiElement): String =
        when (element) {
            is BLuaClassDeclStmt -> element.idToken.text
            is BLuaNamedFunctionStmt -> element.idToken?.text ?: "<unnamed function>"
            is BLuaParam -> element.idToken.text
            is BLuaField -> element.idToken.text
            is BLuaTypedId -> element.idToken.text
            else -> ""
        }

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String =
        when (element) {
            is BLuaClassDeclStmt -> element.text
            is BLuaNamedFunctionStmt -> element.text
            is BLuaParam -> "${element.idToken.text}: ${element.type.text}"
            is BLuaField -> "${element.idToken.text}${
                if (element.type != null) ": " + element.type!!.text else ""
            }"
            is BLuaTypedId -> "${element.idToken.text}${
                if (element.type != null) ": " + element.type!!.text else ""
            }"
            else -> ""
        }
}
